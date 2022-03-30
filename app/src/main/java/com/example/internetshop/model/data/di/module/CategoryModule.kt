package com.example.internetshop.model.data.di.module

import com.example.internetshop.data.retrofitapi.CategoryApi
import com.example.internetshop.model.data.factory.NotificationCategoryFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CategoryModule {
    @Provides
    fun getCategoryApi(retrofit: Retrofit): CategoryApi {
        return retrofit.create(CategoryApi::class.java)
    }

    @Provides
    fun getNotificationCategoryFactory(): NotificationCategoryFactory {
        return NotificationCategoryFactory()
    }
}