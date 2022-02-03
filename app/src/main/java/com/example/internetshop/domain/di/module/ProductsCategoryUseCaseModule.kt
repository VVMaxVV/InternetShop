package com.example.internetshop.domain.di.module

import com.example.internetshop.domain.data.usecase.GetProductsFromCategoryUseCase
import com.example.internetshop.domain.data.usecase.impl.GetProductsFromCategoryUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface ProductsCategoryUseCaseModule {
    @Binds
    fun getProductsCategoryUseCase(
        productsCategoryUseCaseImpl: GetProductsFromCategoryUseCaseImpl
    ): GetProductsFromCategoryUseCase
}