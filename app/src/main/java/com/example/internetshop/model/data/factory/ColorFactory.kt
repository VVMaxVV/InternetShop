package com.example.internetshop.model.data.factory

import javax.inject.Inject

class ColorFactory @Inject constructor(){
    fun get(color: Int): List<String> {
        return when (color) {
            0 -> listOf("Black", "White", "Grey")
            1 -> listOf("Red", "Blue")
            2 -> listOf("Orange", "Green", "Blue", "Purple")
            3 -> listOf("Blue", "Black")
            else -> listOf("Red", "Green", "Blue")
        }
    }
}