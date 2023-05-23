package com.fahmi.mywaifucompose.viewmodel

import androidx.lifecycle.ViewModel
import com.fahmi.mywaifucompose.data.WaifusRepository
import com.fahmi.mywaifucompose.model.Waifu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(private val waifusRepository: WaifusRepository) : ViewModel() {
    fun getWaifuData(idWaifu: String): StateFlow<Waifu> = MutableStateFlow(
        waifusRepository.getWaifuById(idWaifu)
    ).asStateFlow()
}