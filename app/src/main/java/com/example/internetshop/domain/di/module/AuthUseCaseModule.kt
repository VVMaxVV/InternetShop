package com.example.internetshop.domain.di.module

import com.example.internetshop.domain.data.repository.AuthRepository
import com.example.internetshop.domain.data.usecase.GetAuthUseCase
import com.example.internetshop.domain.data.usecase.impl.GetAuthUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class AuthUseCaseModule {
    @Provides
    fun getAuthUseCase(authRepository: AuthRepository): GetAuthUseCase {
        return GetAuthUseCaseImpl(authRepository)
    }
}