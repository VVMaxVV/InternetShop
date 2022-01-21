package com.example.internetshop.domain.data.usecase

import com.example.internetshop.domain.data.model.Token
import com.example.internetshop.domain.data.model.UserCredentials
import io.reactivex.Single

interface AuthUseCase {
    fun execute(userCredentials: UserCredentials): Single<Token>
}