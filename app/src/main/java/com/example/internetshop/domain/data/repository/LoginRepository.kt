package com.example.internetshop.domain.data.repository

import com.example.internetshop.domain.data.model.UserCredentials
import com.example.internetshop.model.interfaces.TokenCallback

interface LoginRepository {
    fun logIn(userCredentials: UserCredentials, tokenCallback: TokenCallback)
}