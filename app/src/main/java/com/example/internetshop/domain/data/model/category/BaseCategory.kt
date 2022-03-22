package com.example.internetshop.domain.data.model.category

import android.graphics.Color

sealed class BaseCategory {
    data class Category(val name: String, val url: String) : BaseCategory()
    data class Notification(
        val name: String,
        val descriptor: String,
        val backgroundColor: Color
    ) : BaseCategory()
}