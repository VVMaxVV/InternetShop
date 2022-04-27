package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.GetAllIdFromDBUseCase
import com.example.internetshop.domain.data.usecase.GetFavoriteUseCase
import com.example.internetshop.domain.data.usecase.GetResultUpdateFavoriteProductsUseCase
import com.example.internetshop.domain.data.usecase.GetUpdateProductsInDBUseCase
import com.example.internetshop.domain.data.usecase.impl.GetAllIdFromDBUseCaseImpl
import com.example.internetshop.domain.data.usecase.impl.GetFavoriteUseCaseImpl
import com.example.internetshop.domain.data.usecase.impl.GetResultUpdateFavoriteProductUseCaseImpl
import com.example.internetshop.domain.data.usecase.impl.GetUpdateProductsInDBUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface FavoritesUseCaseModule {
    @Binds
    fun provideGetFavoriteUseCase(
        getFavoriteUseCaseImpl: GetFavoriteUseCaseImpl
    ): GetFavoriteUseCase

    @Binds
    fun provideGetAllIdFromDBUseCase(
        allIdFromDBUseCaseImpl: GetAllIdFromDBUseCaseImpl
    ): GetAllIdFromDBUseCase

    @Binds
    fun provideGetResultUpdateFavoriteProductUseCase(
        getResultUpdateFavoriteProductUseCaseImpl: GetResultUpdateFavoriteProductUseCaseImpl
    ): GetResultUpdateFavoriteProductsUseCase

    @Binds
    fun provideGetUpdateProductsInDBUseCase(
        getUpdateProductsInDBUseCaseImpl: GetUpdateProductsInDBUseCaseImpl
    ): GetUpdateProductsInDBUseCase
}