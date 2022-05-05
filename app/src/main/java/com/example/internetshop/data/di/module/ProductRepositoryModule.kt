package com.example.internetshop.data.di.module

import com.example.internetshop.data.repository.ProductRemoteRepositoryImpl
import com.example.internetshop.domain.data.repository.BagRepository
import com.example.internetshop.domain.data.repository.ProductLocalRepository
import com.example.internetshop.domain.data.repository.ProductRemoteRepository
import com.example.internetshop.model.implementation.BagRepositoryImpl
import com.example.internetshop.model.implementation.ProductLocalRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface ProductRepositoryModule {
    @Binds
    fun getProductRemoteRepository(impl: ProductRemoteRepositoryImpl): ProductRemoteRepository

    @Binds
    fun getProductLocalRepository(impl: ProductLocalRepositoryImpl): ProductLocalRepository

    @Binds
    fun getBagRepository(impl: BagRepositoryImpl): BagRepository
}