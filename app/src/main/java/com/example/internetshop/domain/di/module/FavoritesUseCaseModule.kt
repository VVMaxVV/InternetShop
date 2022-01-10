package com.example.internetshop.domain.di.module

import com.example.internetshop.domain.data.repository.ProductRepositoryCash
import com.example.internetshop.domain.data.usecase.GetFavoriteUseCase
import com.example.internetshop.domain.data.usecase.impl.GetFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class FavoritesUseCaseModule {
    @Provides
    fun getFavoriteUseCase(productRepository: ProductRepositoryCash): GetFavoriteUseCase {
        return GetFavoriteUseCaseImpl(productRepository)
    }
}