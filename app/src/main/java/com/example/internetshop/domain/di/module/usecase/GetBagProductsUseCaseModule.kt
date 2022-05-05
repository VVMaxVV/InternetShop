package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.GetBagProductsUseCase
import com.example.internetshop.domain.data.usecase.impl.GetBagProductsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface GetBagProductsUseCaseModule {
    @Binds
    fun getBagProductsUseCaseModule(impl: GetBagProductsUseCaseImpl): GetBagProductsUseCase
}