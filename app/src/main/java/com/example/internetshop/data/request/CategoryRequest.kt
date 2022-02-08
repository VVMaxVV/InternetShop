package com.example.internetshop.data.request

class CategoryRequest(category: String) {
    private var categoryName: String? = null
    fun getCategoryName(): String? = categoryName
    init {
        categoryName =  category.lowercase()
    }
}