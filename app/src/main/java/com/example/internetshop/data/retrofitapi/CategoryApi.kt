package com.example.internetshop.data.retrofitapi

import com.example.internetshop.data.response.ProductResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryApi {
    @GET("/products/categories")
    fun getCategoryList(): Single<List<String>>

    @GET("/products/category/{categoryName}")
    fun getCategoryProducts(
        @Path("categoryName") categoryRequest: String
    ): Single<List<ProductResponse>>
}