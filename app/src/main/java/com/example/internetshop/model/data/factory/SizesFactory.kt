package com.example.internetshop.model.data.factory

import javax.inject.Inject

class SizesFactory @Inject constructor(){
    fun get(preset: Int): List<String> {
        return when(preset) {
            0 -> listOf("XS","S")
            1 -> listOf("S","L","XL")
            2 -> listOf("M","XL","XXL")
            3 -> listOf("XS","M","XXL")
            else -> listOf("XS","S","M","L","XL","XXL")
        }
    }
}