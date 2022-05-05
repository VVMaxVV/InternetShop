package com.example.internetshop.data.di.module

import com.example.internetshop.data.repository.ProductRepositoryServerImpl
import com.example.internetshop.domain.data.repository.BagRepository
import com.example.internetshop.domain.data.repository.ProductRepository
import com.example.internetshop.domain.data.repository.ProductRepositoryCash
import com.example.internetshop.model.implementation.BagRepositoryImpl
import com.example.internetshop.model.implementation.ProductRepositoryCashImpl
import dagger.Binds
import dagger.Module

@Module
interface ProductRepositoryModule {
    @Binds
    fun getProductRepository(impl: ProductRepositoryServerImpl): ProductRepository

    @Binds
    fun getProductRepositoryCash(impl: ProductRepositoryCashImpl): ProductRepositoryCash

    @Binds
    fun getBagRepository(impl: BagRepositoryImpl): BagRepository
}