package com.example.internetshop.model.data.remote

import com.example.internetshop.model.data.dataclass.ProductItem
import com.example.internetshop.model.data.dataclass.ProductListItems
import com.example.internetshop.model.data.dataclass.Token
import com.example.internetshop.model.data.dataclass.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductsApi {
    @GET("/products")
    fun getProductList(): Call<List<ProductListItems>>

    @GET("/products/{id}")
    fun getProduct(
        @Path("id") id: String
    ) :Call<ProductItem>

    @POST("/auth/login")
    fun login(@Body user: User): Call<Token>
}