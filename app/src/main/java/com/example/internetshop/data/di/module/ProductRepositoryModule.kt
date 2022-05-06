package com.example.internetshop.data.di.module

import com.example.internetshop.data.repository.ProductRemoteRepositoryImpl
import com.example.internetshop.domain.data.repository.ProductLocalRepository
import com.example.internetshop.domain.data.repository.ProductRemoteRepository
import com.example.internetshop.model.implementation.ProductLocalRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface ProductRepositoryModule {
    @Binds
    fun getProductRepository(impl: ProductRemoteRepositoryImpl): ProductRemoteRepository

    @Binds
    fun getProductRepositoryCash(impl: ProductLocalRepositoryImpl): ProductLocalRepository
}