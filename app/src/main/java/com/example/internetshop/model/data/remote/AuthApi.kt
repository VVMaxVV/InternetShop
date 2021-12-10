package com.example.internetshop.model.data.remote

import com.example.internetshop.model.data.dataclass.Token
import com.example.internetshop.model.data.dataclass.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/login")
    fun login(@Body user: User): Call<Token>
}