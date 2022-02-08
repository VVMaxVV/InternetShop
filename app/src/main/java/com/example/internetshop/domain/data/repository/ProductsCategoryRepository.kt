package com.example.internetshop.domain.data.repository

import com.example.internetshop.data.request.CategoryRequest
import com.example.internetshop.domain.data.model.product.SimpleProduct
import io.reactivex.Single

interface ProductsCategoryRepository {
    fun getProductsCategory(categoryRequest: CategoryRequest): Single<List<SimpleProduct>>
}