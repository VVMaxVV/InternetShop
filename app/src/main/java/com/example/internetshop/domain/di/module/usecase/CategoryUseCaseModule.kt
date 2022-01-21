package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.GetCategoriesUseCase
import com.example.internetshop.domain.data.usecase.impl.GetCategoriesUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface CategoryUseCaseModule {
    @Binds
    fun getCategoryUseCase(categoriesUseCaseImpl: GetCategoriesUseCaseImpl): GetCategoriesUseCase
}