package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.Token
import com.example.internetshop.domain.data.model.UserCredentials
import com.example.internetshop.domain.data.repository.AuthRepository
import com.example.internetshop.domain.data.usecase.AuthUseCase
import io.reactivex.Single
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(private val authRepository: AuthRepository): AuthUseCase {
    override fun execute(userCredentials: UserCredentials): Single<Token> {
        return authRepository.logIn(userCredentials)
    }
}