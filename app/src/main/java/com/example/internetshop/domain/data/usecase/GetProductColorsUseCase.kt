package com.example.internetshop.domain.data.usecase

import io.reactivex.Single

interface GetProductColorsUseCase {
    fun execute(): Single<List<String>>
}