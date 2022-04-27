package com.example.internetshop.domain.data.usecase

import io.reactivex.Completable

interface GetResultUpdateFavoriteProductsUseCase {
    fun execute(): Completable
}