package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.GetProductsFromServerUseCase
import com.example.internetshop.domain.data.usecase.impl.GetProductsFromServerUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface ProductsFromServerUseCaseModule {
    @Binds
    fun getProductsFromServerUseCase(
        productsFromServerUseCaseImpl: GetProductsFromServerUseCaseImpl
    ): GetProductsFromServerUseCase

}