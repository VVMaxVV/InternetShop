package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.data.response.TokenResponse
import com.example.internetshop.data.response.mapper.TokenMapper
import com.example.internetshop.domain.data.model.Token
import com.example.internetshop.domain.data.usecase.GetTokenUseCase
import java.util.*
import javax.inject.Inject

class GetTokenUseCaseImpl @Inject constructor(private val tokenMapper: TokenMapper): GetTokenUseCase {
    override fun execute(tokenResponse: TokenResponse): Token {
        tokenMapper.toToken(tokenResponse).apply {
            val calendar = Calendar.getInstance();
            calendar.time = this.date;
            calendar.add(Calendar.DATE, 10);
            return Token(this.token,calendar.time)
        }
    }
}