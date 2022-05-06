package com.example.internetshop.domain.data.usecase

import io.reactivex.Single

interface GetAllFavoriteIdsUseCase {
    fun execute(): Single<List<String>>
}