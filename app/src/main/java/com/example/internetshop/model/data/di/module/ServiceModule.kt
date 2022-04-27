package com.example.internetshop.model.data.di.module

import com.example.internetshop.presentation.service.UpdateFavoriteProductDataService
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {
    @Provides
    fun provideUpdateFavoriteProductDateService(): UpdateFavoriteProductDataService {
        return UpdateFavoriteProductDataService()
    }
}