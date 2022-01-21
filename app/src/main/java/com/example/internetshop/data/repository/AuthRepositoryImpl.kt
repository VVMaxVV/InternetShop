package com.example.internetshop.data.repository

import com.example.internetshop.data.response.mapper.UserCredentialsMapper
import com.example.internetshop.data.retrofitapi.AuthApi
import com.example.internetshop.domain.data.model.Token
import com.example.internetshop.domain.data.model.UserCredentials
import com.example.internetshop.domain.data.repository.AuthRepository
import com.example.internetshop.domain.data.usecase.GetTokenUseCase
import io.reactivex.Single
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val userCredentialsMapper: UserCredentialsMapper,
    private val tokenUseCase: GetTokenUseCase
) : AuthRepository {
    override fun logIn(userCredentials: UserCredentials): Single<Token> {
        return authApi.login(userCredentialsMapper.toRequest(userCredentials)).map {
            tokenUseCase.execute(it)
        }
    }
}