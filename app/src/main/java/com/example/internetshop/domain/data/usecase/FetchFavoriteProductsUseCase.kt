package com.example.internetshop.domain.data.usecase

import io.reactivex.Completable

interface FetchFavoriteProductsUseCase {
    fun execute(): Completable
}