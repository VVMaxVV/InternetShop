package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.DeleteFromFavoriteUseCase
import com.example.internetshop.domain.data.usecase.impl.DeleteFromFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface DeleteFromFavoriteUseCaseModule {
    @Binds
    fun getDeleteFromFavoriteUseCase(impl: DeleteFromFavoriteUseCaseImpl): DeleteFromFavoriteUseCase
}