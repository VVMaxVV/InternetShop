package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.GetTokenUseCase
import com.example.internetshop.domain.data.usecase.impl.GetTokenUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface TokenUseCaseModule {
    @Binds
    fun getTokenUseCase(tokenUseCaseImpl: GetTokenUseCaseImpl): GetTokenUseCase
}