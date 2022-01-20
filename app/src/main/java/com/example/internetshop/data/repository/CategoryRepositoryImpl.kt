package com.example.internetshop.data.repository

import com.example.internetshop.data.response.CategoryResponse
import com.example.internetshop.data.response.mapper.CategoryResponseMapper
import com.example.internetshop.data.retrofitapi.CategoryApi
import com.example.internetshop.domain.data.model.Category
import com.example.internetshop.domain.data.repository.CategoryRepository
import io.reactivex.Single
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryApi: CategoryApi,
    private val categoryResponseMapper: CategoryResponseMapper
) : CategoryRepository {
    override fun getCategoryList(): Single<List<Category>> {

        return categoryApi.getCategoryList().map {
            categoryResponseMapper.toCategory(CategoryResponse(it))
        }
    }
}