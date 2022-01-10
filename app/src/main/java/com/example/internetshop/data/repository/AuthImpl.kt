package com.example.internetshop.data.repository

import android.accounts.NetworkErrorException
import com.example.internetshop.data.response.mapper.UserCredentialsMapper
import com.example.internetshop.data.retrofitapi.AuthApi
import com.example.internetshop.domain.data.model.Token
import com.example.internetshop.domain.data.model.UserCredentials
import com.example.internetshop.domain.data.repository.LoginRepository
import com.example.internetshop.model.interfaces.TokenCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AuthImpl @Inject constructor(
    private val authApi: AuthApi,
    private val userCredentialsMapper: UserCredentialsMapper
) : LoginRepository {

    override fun logIn(userCredentials: UserCredentials, tokenCallback: TokenCallback) {
        authApi.login(userCredentialsMapper.toRequest(userCredentials))
            .enqueue(object : Callback<Token> {
                override fun onResponse(call: Call<Token>?, response: Response<Token>?) {
                    val responseBody = response?.body()
                    if (responseBody != null) {
                        tokenCallback.onSuccess(responseBody)
                    } else {
                        tokenCallback.onFail(IllegalStateException("Empty body"))
                    }
                }

                override fun onFailure(call: Call<Token>?, t: Throwable?) {
                    tokenCallback.onFail(t ?: NetworkErrorException())
                }
            })
    }
}