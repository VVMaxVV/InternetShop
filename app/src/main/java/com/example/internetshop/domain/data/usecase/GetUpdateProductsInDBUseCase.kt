package com.example.internetshop.domain.data.usecase

import com.example.internetshop.domain.data.model.product.Product
import io.reactivex.Completable

interface GetUpdateProductsInDBUseCase {
    fun execute(productList: List<Product>): Completable
}