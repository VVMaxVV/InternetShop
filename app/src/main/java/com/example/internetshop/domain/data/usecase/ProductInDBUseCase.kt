package com.example.internetshop.domain.data.usecase

import dagger.Module
import io.reactivex.Single

@Module
interface ProductInDBUseCase {
    fun execute(id: String): Single<Boolean>
}