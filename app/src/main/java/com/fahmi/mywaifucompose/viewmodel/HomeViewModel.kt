package com.fahmi.mywaifucompose.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fahmi.mywaifucompose.data.WaifusRepository
import com.fahmi.mywaifucompose.model.Waifu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val waifusRepository: WaifusRepository) : ViewModel() {
    private val _groupedWaifus = MutableStateFlow(
        waifusRepository.getWaifus()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedWaifus: StateFlow<Map<Char, List<Waifu>>> get() = _groupedWaifus

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedWaifus.value = waifusRepository.searchWaifus(newQuery)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}