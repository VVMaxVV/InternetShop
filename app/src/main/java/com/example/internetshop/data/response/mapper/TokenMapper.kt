package com.example.internetshop.data.response.mapper

import com.example.internetshop.data.response.TokenResponse
import com.example.internetshop.domain.data.model.Token
import java.util.*
import javax.inject.Inject

class TokenMapper @Inject constructor() {
    fun toToken(token: TokenResponse): Token {
        return Token(token.token, Date())
    }
}