package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.AuthUseCase
import com.example.internetshop.domain.data.usecase.impl.AuthUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface AuthUseCaseModule {
    @Binds
    fun getAuthUseCase(authUseCaseImpl: AuthUseCaseImpl): AuthUseCase
}