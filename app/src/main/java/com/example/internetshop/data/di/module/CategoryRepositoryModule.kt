package com.example.internetshop.data.di.module

import com.example.internetshop.data.repository.ProductsCategoryRepositoryImpl
import com.example.internetshop.domain.data.repository.ProductsCategoryRepository
import dagger.Binds
import dagger.Module

@Module
interface CategoryRepositoryModule {
    @Binds
    fun getCategoryRepository(
        productsCategoryRepositoryImpl: ProductsCategoryRepositoryImpl
    ): ProductsCategoryRepository
}