package com.example.internetshop.model.data.di.module

import com.example.internetshop.model.implementation.ReviewRepositoryImpl
import com.example.internetshop.model.interfaces.ReviewRepository
import dagger.Module
import dagger.Provides

@Module
class ReviewModule {
    @Provides
    fun getReviewRepository() : ReviewRepository {
        return ReviewRepositoryImpl()
    }
}