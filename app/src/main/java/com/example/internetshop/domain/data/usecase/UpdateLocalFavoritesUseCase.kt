package com.example.internetshop.domain.data.usecase

import com.example.internetshop.domain.data.model.product.Product
import io.reactivex.Completable

interface UpdateLocalFavoritesUseCase {
    fun execute(productList: List<Product>): Completable
}