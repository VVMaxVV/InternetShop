package com.example.internetshop.data.request.mapper

import javax.inject.Inject

class CategoryRequestMapper @Inject constructor() {
    fun toRequest(categoryName: String): String =
        categoryName.lowercase()
}