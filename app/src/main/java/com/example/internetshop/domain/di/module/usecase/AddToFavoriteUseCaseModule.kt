package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.AddToFavoriteUseCase
import com.example.internetshop.domain.data.usecase.impl.AddToFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface AddToFavoriteUseCaseModule {
    @Binds
    fun getAddToFavoriteUseCase(impl: AddToFavoriteUseCaseImpl): AddToFavoriteUseCase
}