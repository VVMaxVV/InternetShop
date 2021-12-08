package com.example.internetshop.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.internetshop.R
import com.example.internetshop.model.data.remote.QuestApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Products : AppCompatActivity() {

    lateinit var questApi: QuestApi

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

        questApi = retrofit.create(Products::class.java)
    }
}