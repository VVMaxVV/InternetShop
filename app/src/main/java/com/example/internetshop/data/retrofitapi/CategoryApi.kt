package com.example.internetshop.data.retrofitapi

import com.example.internetshop.data.response.CategoryResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CategoryApi {
    @GET("/products/categories")
    fun getCategoryList(): Single<List<CategoryResponse>>
}