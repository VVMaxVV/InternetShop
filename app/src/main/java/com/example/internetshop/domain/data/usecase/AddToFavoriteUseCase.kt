package com.example.internetshop.domain.data.usecase

import com.example.internetshop.domain.data.model.product.Product
import io.reactivex.Completable

interface AddToFavoriteUseCase {
    fun execute(product: Product): Completable
}