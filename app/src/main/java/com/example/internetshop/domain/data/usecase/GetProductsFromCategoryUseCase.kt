package com.example.internetshop.domain.data.usecase

import com.example.internetshop.data.request.CategoryRequest
import com.example.internetshop.domain.data.model.product.SimpleProduct
import io.reactivex.Single

interface GetProductsFromCategoryUseCase {
    fun execute(categoryRequest: CategoryRequest): Single<List<SimpleProduct>>
}