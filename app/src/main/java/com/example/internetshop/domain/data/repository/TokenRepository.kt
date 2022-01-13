package com.example.internetshop.domain.data.repository

import com.example.internetshop.domain.data.model.Token
import io.reactivex.Single

interface TokenRepository {
    fun getToken(): Single<Token>
}