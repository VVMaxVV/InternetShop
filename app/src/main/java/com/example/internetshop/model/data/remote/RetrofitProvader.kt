package com.example.internetshop.model.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvader {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var api: ProductsApi? = null
    fun getAPI():ProductsApi {
        if(api==null) {api=retrofit.create(ProductsApi::class.java)}
        return api!!
    }
}