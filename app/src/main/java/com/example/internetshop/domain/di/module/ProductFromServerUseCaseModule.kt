package com.example.internetshop.domain.di.module

import com.example.internetshop.domain.data.repository.ProductRepository
import com.example.internetshop.domain.data.usecase.GetProductFromServerUseCase
import com.example.internetshop.domain.data.usecase.impl.GetProductFromServerUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class ProductFromServerUseCaseModule {
    @Provides
    fun getProductFromServerUseCase(productRepository: ProductRepository): GetProductFromServerUseCase {
        return GetProductFromServerUseCaseImpl(productRepository)
    }
}