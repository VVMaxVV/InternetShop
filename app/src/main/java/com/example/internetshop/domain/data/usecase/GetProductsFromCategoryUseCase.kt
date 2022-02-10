package com.example.internetshop.domain.data.usecase

import com.example.internetshop.domain.data.model.product.SimpleProduct
import io.reactivex.Single

interface GetProductsFromCategoryUseCase {
    fun execute(categoryName: String): Single<List<SimpleProduct>>
}