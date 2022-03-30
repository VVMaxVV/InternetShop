package com.example.internetshop.domain.data.repository

import com.example.internetshop.domain.data.model.category.Category
import io.reactivex.Single

interface CategoryRepository {
    fun getCategoryList(): Single<List<Category>>
}