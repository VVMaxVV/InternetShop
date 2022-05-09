package com.example.internetshop.domain.data.usecase

import io.reactivex.Single

interface GetProductSizesUseCase {
    fun execute(): Single<List<String>>
}