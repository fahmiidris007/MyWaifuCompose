package com.fahmi.mywaifucompose.data

import com.fahmi.mywaifucompose.model.Waifu
import com.fahmi.mywaifucompose.model.WaifusData

class WaifusRepository {
    fun getWaifus(): List<Waifu> {
        return WaifusData.waifus
    }

    fun searchWaifus(query: String): List<Waifu> {
        return WaifusData.waifus.filter { waifu ->
            waifu.name.contains(query, ignoreCase = true)
        }
    }

    fun getWaifuById(id: String): Waifu {
        return WaifusData.waifus.find {
            it.id == id
        } as Waifu
    }
}