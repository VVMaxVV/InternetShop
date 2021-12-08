package com.example.internetshop.model.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface ProductsApi {
    @GET("/products")
    fun getProductList(): Call<List<ProductListItems>>

    @GET("/products/")
}