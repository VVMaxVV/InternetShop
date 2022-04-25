package com.example.internetshop.model.data.di.module

import com.example.internetshop.presentation.service.UpdateFavoriteProductDateService
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {
    @Provides
    fun provideUpdateFavoriteProductDateService(): UpdateFavoriteProductDateService {
        return UpdateFavoriteProductDateService()
    }
}