package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.FetchFavoriteProductsUseCase
import com.example.internetshop.domain.data.usecase.GetAllFavoriteIdsUseCase
import com.example.internetshop.domain.data.usecase.GetFavoriteUseCase
import com.example.internetshop.domain.data.usecase.UpdateLocalFavoritesUseCase
import com.example.internetshop.domain.data.usecase.impl.FetchFavoriteProductUseCaseImpl
import com.example.internetshop.domain.data.usecase.impl.GetAllFavoriteIdsUseCaseImpl
import com.example.internetshop.domain.data.usecase.impl.GetFavoriteUseCaseImpl
import com.example.internetshop.domain.data.usecase.impl.UpdateLocalFavoritesUseCaseImpl
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
        allIdFromDBUseCaseImpl: GetAllFavoriteIdsUseCaseImpl
    ): GetAllFavoriteIdsUseCase

    @Binds
    fun provideGetResultUpdateFavoriteProductUseCase(
        getResultUpdateFavoriteProductUseCaseImpl: FetchFavoriteProductUseCaseImpl
    ): FetchFavoriteProductsUseCase

    @Binds
    fun provideGetUpdateProductsInDBUseCase(
        getUpdateProductsInDBUseCaseImplImpl: UpdateLocalFavoritesUseCaseImpl
    ): UpdateLocalFavoritesUseCase
}