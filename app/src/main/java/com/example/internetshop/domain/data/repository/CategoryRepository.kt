package com.example.internetshop.domain.data.repository

import com.example.internetshop.domain.data.model.Category
import io.reactivex.Single

interface CategoryRepository {
    fun getCategoryList(): Single<List<Category>>
}