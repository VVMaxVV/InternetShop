package com.example.internetshop.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.internetshop.R
import com.example.internetshop.model.data.dataclass.ProductItem
import com.example.internetshop.model.data.remote.ProductsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsActvity : AppCompatActivity() {

    lateinit var productsApi: ProductsApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        configurateRetrofits()
    }

    private fun configurateRetrofits() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        productsApi = retrofit.create(ProductsApi::class.java)
//        productsApi.getProductList().enqueue(object : Callback<List<ProductListItems>>{
//            override fun onResponse(
//                call: Call<List<ProductListItems>>?,
//                response: Response<List<ProductListItems>>?
//            ) {
//                Log.i("API123","${response?.body()}")
//            }
//
//            override fun onFailure(call: Call<List<ProductListItems>>?, t: Throwable?) {
//                Log.i("API123", "${t.toString()}")
//            }
//        })
        productsApi.getProduct("1").enqueue(object : Callback<ProductItem> {
            override fun onResponse(call: Call<ProductItem>?, response: Response<ProductItem>?) {
                Log.i("API_SINGLE","${response.toString()}")
            }

            override fun onFailure(call: Call<ProductItem>?, t: Throwable?) {
                Log.i("API_SINGLE", "${t.toString()}")
            }

        })

    }
}