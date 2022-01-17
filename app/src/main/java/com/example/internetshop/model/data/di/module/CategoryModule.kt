package com.example.internetshop.model.data.di.module

import com.example.internetshop.data.repository.CategoryRepositoryImpl
import com.example.internetshop.data.response.mapper.CategoryResponseMapper
import com.example.internetshop.data.retrofitapi.CategoryApi
import com.example.internetshop.domain.data.repository.CategoryRepository
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
    fun getCategoryRepository(
        categoryApi: CategoryApi,
        categoryResponseMapper: CategoryResponseMapper
    ): CategoryRepository {
        return CategoryRepositoryImpl(categoryApi, categoryResponseMapper)
    }

    @Provides
    fun getCategoryResponseMapper(): CategoryResponseMapper {
        return CategoryResponseMapper()
    }
}