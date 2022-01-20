package com.example.internetshop.domain.data.usecase

import com.example.internetshop.data.response.TokenResponse
import com.example.internetshop.domain.data.model.Token

interface GetTokenUseCase {
    fun execute(tokenResponse: TokenResponse) : Token
}