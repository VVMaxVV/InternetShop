package com.example.internetshop.model.implementation

import android.accounts.NetworkErrorException
import com.example.internetshop.model.data.dataclass.Token
import com.example.internetshop.model.data.dataclass.User
import com.example.internetshop.model.data.remote.AuthApi
import com.example.internetshop.model.interfaces.LoginRepository
import com.example.internetshop.model.interfaces.TokenCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AuthImpl @Inject constructor(private val authApi: AuthApi): LoginRepository {

    override fun logIn(username: String, password: String, tokenCallback: TokenCallback) {
        authApi.login(User(username, password)).enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>?, response: Response<Token>?) {
                val responseBody = response?.body()
                if(responseBody!=null) {
                    tokenCallback.onSuccess(responseBody)
                } else {tokenCallback.onFail(IllegalStateException("Empty body"))}
            }
            override fun onFailure(call: Call<Token>?, t: Throwable?) {
                tokenCallback.onFail(t?: NetworkErrorException())
            }
        })
    }
}