package com.example.internetshop.domain.data.usecase

import com.example.internetshop.domain.data.model.product.Product
import io.reactivex.Single

interface GetProductsFromServerUseCase {
    fun execute(): Single<List<Product>>
}