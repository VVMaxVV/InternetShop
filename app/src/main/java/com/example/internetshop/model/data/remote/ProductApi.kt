package com.example.internetshop.model.data.remote

import com.example.internetshop.Product
import com.example.internetshop.model.data.dataclass.ProductItem
import com.example.internetshop.model.data.dataclass.ProductListItems
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("/products")
    fun getProductList(): Call<List<ProductListItems>>

    @GET("/products/{id}")
    fun getProduct(
        @Path("id") id: String
    ): Call<ProductItem>

    @GET("/products/{id}")
    fun getProductRx(
        @Path("id") id: String
    ): Single<Product>
}