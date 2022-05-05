package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.UpdateBagProductUseCase
import com.example.internetshop.domain.data.usecase.impl.UpdateBagProductUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UpdateBagProductUseCaseModule {
    @Binds
    fun getUpdateBagProductUseCaseModule(impl: UpdateBagProductUseCaseImpl): UpdateBagProductUseCase
}