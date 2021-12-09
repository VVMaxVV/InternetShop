package com.example.internetshop.model.implementation

import com.example.internetshop.model.data.dataclass.SingleRequestBody
import com.example.internetshop.model.data.dataclass.Token
import com.example.internetshop.model.data.dataclass.User
import com.example.internetshop.model.data.remote.RetrofitProvider
import com.example.internetshop.model.interfaces.LoginRepository
import com.example.internetshop.model.interfaces.TokenCallback
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthImpl: LoginRepository {
    private val api = RetrofitProvider.getAPI()
    override fun logIn(username: String, password: String, tokenCallback: TokenCallback) {
        api.login(SingleRequestBody(Gson().toJson(User(username,password)))).enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>?, response: Response<Token>?) {
                val responseBody = response?.body()
                if(responseBody!=null) {
                    responseBody.onSuccess(responseBody)
                } else {tokenCallback.onFail(IllegalStateException("Empty body"))}
            }

            override fun onFailure(call: Call<Token>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })
    }
//    override fun logIn(username: String, password: String, LoginCallback: LoginCallback) {
//        api.login(SingleRequestBody(Gson().toJson(User(username,password)))).enqueue(object : Callback<Token> {
//            override fun onResponse(call: retrofit2.Call<Token>?, response: Response<Token>?) {
//
//            }
//
//            override fun onFailure(call: retrofit2.Call<Token>?, t: Throwable?) {
//                TODO("Not yet implemented")
//            }
//
//        })
    }
}