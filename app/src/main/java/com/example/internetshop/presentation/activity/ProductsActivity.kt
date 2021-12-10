package com.example.internetshop.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.internetshop.R
import com.example.internetshop.model.data.dataclass.ProductItem
import com.example.internetshop.model.data.remote.ProductApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsActivity : AppCompatActivity() {

    lateinit var productApi: ProductApi

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

        productApi = retrofit.create(ProductApi::class.java)

        productApi.getProduct("1").enqueue(object : Callback<ProductItem> {
            override fun onResponse(call: Call<ProductItem>?, response: Response<ProductItem>?) {
                Log.i("API_SINGLE","${response.toString()}")
            }

            override fun onFailure(call: Call<ProductItem>?, t: Throwable?) {
                Log.i("API_SINGLE", "${t.toString()}")
            }

        })

    }
}