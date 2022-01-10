package com.example.internetshop.domain.di.module

import com.example.internetshop.domain.data.repository.ProductRepository
import com.example.internetshop.domain.data.usecase.GetProductsFromServerUseCase
import com.example.internetshop.domain.data.usecase.impl.GetProductsFromServerUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class ProductsFromServerUseCaseModule {
    @Provides
    fun getProductsFromServerUseCase(productRepository: ProductRepository) : GetProductsFromServerUseCase {
        return GetProductsFromServerUseCaseImpl(productRepository)
    }
}