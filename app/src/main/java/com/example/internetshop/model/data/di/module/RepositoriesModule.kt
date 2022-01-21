package com.example.internetshop.model.data.di.module

import com.example.internetshop.data.repository.AuthRepositoryImpl
import com.example.internetshop.data.repository.CategoryRepositoryImpl
import com.example.internetshop.domain.data.repository.AuthRepository
import com.example.internetshop.domain.data.repository.CategoryRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoriesModule {
    @Binds
    fun getAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun getCategoriesRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository
}