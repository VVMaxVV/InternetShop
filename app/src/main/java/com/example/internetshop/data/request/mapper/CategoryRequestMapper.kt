package com.example.internetshop.data.request.mapper

import com.example.internetshop.data.request.CategoryRequest
import javax.inject.Inject

class CategoryRequestMapper @Inject constructor() {
    fun toRequest(categoryName: String): CategoryRequest =
        CategoryRequest(categoryName.lowercase())
}