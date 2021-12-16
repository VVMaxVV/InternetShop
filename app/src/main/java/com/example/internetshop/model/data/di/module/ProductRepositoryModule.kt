package com.example.internetshop.model.data.di.module

import com.example.internetshop.model.implementation.ProductRepositoryServerImpl
import com.example.internetshop.model.interfaces.ProductRepository
import dagger.Binds
import dagger.Module

@Module
interface ProductRepositoryModule {
    @Binds
    fun getProductRepository(impl: ProductRepositoryServerImpl): ProductRepository
}