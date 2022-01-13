package com.example.internetshop.domain.data.repository

import com.example.internetshop.domain.data.model.Token
import com.example.internetshop.domain.data.model.UserCredentials
import io.reactivex.Single

interface LoginRepository {
    fun logIn(userCredentials: UserCredentials): Single<Token>
}