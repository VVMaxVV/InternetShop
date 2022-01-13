package com.example.internetshop.data.retrofitapi

import com.example.internetshop.data.request.AuthRequest
import com.example.internetshop.data.response.TokenResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/login")
    fun login(@Body user: AuthRequest): Single<TokenResponse>
}