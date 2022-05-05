package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.GetProductSizesUseCase
import com.example.internetshop.domain.data.usecase.impl.GetProductSizesUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface ProductSizesUseCaseModule {
    @Binds
    fun getProductSizesUseCase(impl: GetProductSizesUseCaseImpl): GetProductSizesUseCase
}