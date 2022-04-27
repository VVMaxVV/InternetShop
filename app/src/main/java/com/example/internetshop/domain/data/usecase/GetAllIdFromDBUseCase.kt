package com.example.internetshop.domain.data.usecase

import io.reactivex.Single

interface GetAllIdFromDBUseCase {
    fun execute(): Single<List<String>>
}