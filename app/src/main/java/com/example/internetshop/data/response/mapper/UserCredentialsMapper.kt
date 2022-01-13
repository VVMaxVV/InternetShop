package com.example.internetshop.data.response.mapper

import com.example.internetshop.data.request.AuthRequest
import com.example.internetshop.domain.data.model.UserCredentials
import javax.inject.Inject

class UserCredentialsMapper @Inject constructor() {
    fun toRequest(userCredentials: UserCredentials) : AuthRequest =
        AuthRequest(userCredentials.username,userCredentials.password)
}