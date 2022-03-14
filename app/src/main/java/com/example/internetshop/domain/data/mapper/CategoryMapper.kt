package com.example.internetshop.domain.data.mapper

import com.example.internetshop.domain.data.model.Category
import com.example.internetshop.model.data.viewStates.BaseViewState
import com.example.internetshop.model.data.viewStates.CategoryViewState
import javax.inject.Inject

class CategoryMapper @Inject constructor() {
    fun toCategoryViewState(category: Category) : BaseViewState {
        return CategoryViewState(category.name,category.imageUri)
    }
}