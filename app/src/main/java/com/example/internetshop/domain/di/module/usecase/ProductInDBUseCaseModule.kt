package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.ProductInDBUseCase
import com.example.internetshop.domain.data.usecase.impl.ProductInDBUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface ProductInDBUseCaseModule {
    @Binds
    fun getProductInDBUseCaseModule(impl: ProductInDBUseCaseImpl): ProductInDBUseCase
}