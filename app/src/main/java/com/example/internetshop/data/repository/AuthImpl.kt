package com.example.internetshop.data.repository

import com.example.internetshop.data.response.mapper.TokenMapper
import com.example.internetshop.data.response.mapper.UserCredentialsMapper
import com.example.internetshop.data.retrofitapi.AuthApi
import com.example.internetshop.domain.data.model.Token
import com.example.internetshop.domain.data.model.UserCredentials
import com.example.internetshop.domain.data.repository.LoginRepository
import io.reactivex.Single
import javax.inject.Inject

class AuthImpl @Inject constructor(
    private val authApi: AuthApi,
    private val userCredentialsMapper: UserCredentialsMapper,
    private val tokenMapper: TokenMapper
) : LoginRepository {
    override fun logIn(userCredentials: UserCredentials): Single<Token> {
        return authApi.login(userCredentialsMapper.toRequest(userCredentials)).map {
            tokenMapper.toToken(it)
        }
    }
}