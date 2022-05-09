package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.GetProductColorsUseCase
import com.example.internetshop.domain.data.usecase.impl.GetProductColorsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface ProductColorsUseCaseModule {
    @Binds
    fun getProductColorsUseCase(impl: GetProductColorsUseCaseImpl): GetProductColorsUseCase
}