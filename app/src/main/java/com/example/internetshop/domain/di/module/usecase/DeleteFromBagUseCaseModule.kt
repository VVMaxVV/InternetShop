package com.example.internetshop.domain.di.module.usecase

import com.example.internetshop.domain.data.usecase.DeleteFromBagUseCase
import com.example.internetshop.domain.data.usecase.impl.DeleteFromBagUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface DeleteFromBagUseCaseModule {
    @Binds
    fun getDeleteFromBagUseCase(impl: DeleteFromBagUseCaseImpl): DeleteFromBagUseCase
}