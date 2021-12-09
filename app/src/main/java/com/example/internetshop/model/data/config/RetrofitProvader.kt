package com.example.internetshop.model.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    val interseptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    val client = OkHttpClient.Builder().addInterceptor(interseptor).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private var api: ProductsApi? = null


    fun getAPI() : ProductsApi {
        if(api==null) {api=retrofit.create(ProductsApi::class.java)}
        return api!!
    }
}