package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.AddProductToBagUseCase
import com.example.internetshop.domain.data.usecase.impl.AddProductToBagUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface AddProductToBagUseCaseModule {
    @Binds
    fun getAddPorductToBagUseCase(impl: AddProductToBagUseCaseImpl): AddProductToBagUseCase
}