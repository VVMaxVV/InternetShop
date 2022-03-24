package com.example.internetshop.domain.data.mapper

import com.example.internetshop.domain.data.model.category.BaseCategory
import com.example.internetshop.domain.data.model.category.Category
import com.example.internetshop.model.data.viewStates.BaseViewState
import com.example.internetshop.model.data.viewStates.CategoryViewState
import com.example.internetshop.model.data.viewStates.ErrorViewState
import com.example.internetshop.model.data.viewStates.NotificationViewState
import javax.inject.Inject

class CategoryMapper @Inject constructor() {
    fun toViewState(baseCategory: BaseCategory): BaseViewState {
        return when (baseCategory) {
            is BaseCategory.Category -> CategoryViewState(
                baseCategory.name,
                baseCategory.url
            )
            is BaseCategory.Notification -> NotificationViewState(
                baseCategory.name,
                baseCategory.descriptor,
                baseCategory.backgroundColor
            )
            is BaseCategory.Error -> ErrorViewState()
        }
    }

    fun toBaseCategory(listCategory: List<Category>): List<BaseCategory> {
        return listCategory.map {
            BaseCategory.Category(it.name, it.imageUri)
        }
    }
}