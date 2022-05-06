package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.GetProductFromServerUseCase
import com.example.internetshop.domain.data.usecase.impl.GetProductFromServerUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface ProductFromServerUseCaseModule {
    @Binds
    fun getProductFromServerUseCase(
        getProductFromServerUseCaseImpl: GetProductFromServerUseCaseImpl
    ): GetProductFromServerUseCase
}