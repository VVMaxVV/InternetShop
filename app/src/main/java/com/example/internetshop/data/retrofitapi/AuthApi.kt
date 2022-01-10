package com.example.internetshop.data.retrofitapi

import com.example.internetshop.data.request.AuthRequest
import com.example.internetshop.domain.data.model.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/login")
    fun login(@Body user: AuthRequest): Call<Token>
}