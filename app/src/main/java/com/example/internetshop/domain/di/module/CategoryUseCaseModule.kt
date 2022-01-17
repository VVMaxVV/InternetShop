package com.example.internetshop.domain.di.module

import com.example.internetshop.domain.data.repository.CategoryRepository
import com.example.internetshop.domain.data.usecase.GetCategoryUseCase
import com.example.internetshop.domain.data.usecase.impl.GetCategoryUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class CategoryUseCaseModule {
    @Provides
    fun getCategoryUseCase(categoryRepository: CategoryRepository): GetCategoryUseCase {
        return GetCategoryUseCaseImpl(categoryRepository)
    }
}