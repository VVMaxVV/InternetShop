package com.example.internetshop.data.di.module

import com.example.internetshop.data.repository.ReviewRepositoryImpl
import com.example.internetshop.domain.data.repository.ReviewRepository
import dagger.Module
import dagger.Provides

@Module
class ReviewModule {
    @Provides
    fun getReviewRepository(): ReviewRepository {
        return ReviewRepositoryImpl()
    }
}