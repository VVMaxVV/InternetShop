package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.Token
import com.example.internetshop.domain.data.model.UserCredentials
import com.example.internetshop.domain.data.repository.AuthRepository
import com.example.internetshop.domain.data.usecase.GetAuthUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetAuthUseCaseImpl @Inject constructor(private val authRepository: AuthRepository): GetAuthUseCase {
    override fun execute(userCredentials: UserCredentials): Single<Token> {
        return authRepository.logIn(userCredentials)
    }
}