package com.example.internetshop.domain.data.usecase

import com.example.internetshop.domain.data.model.product.Product
import io.reactivex.Completable

interface DeleteFromFavoriteUseCase {
    fun execute(product: Product): Completable
}