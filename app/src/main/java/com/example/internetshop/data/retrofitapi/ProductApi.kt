package com.example.internetshop.data.retrofitapi

import com.example.internetshop.data.response.ProductResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("/products/{id}")
    fun getProductRx(
        @Path("id") id: String
    ): Single<ProductResponse>

    @GET("/products")
    fun getProductsRx(): Single<List<ProductResponse>>


}