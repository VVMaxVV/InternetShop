package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.model.Token
import com.example.internetshop.domain.data.model.UserCredentials
import com.example.internetshop.domain.data.repository.LoginRepository
import com.example.internetshop.model.interfaces.TokenCallback
import javax.inject.Inject

class AuthenticationActivityViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    BaseViewModel() {
    val tokenResult = MutableLiveData<Token>()
    val textResult = MutableLiveData<String>()

    fun getToken(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            textResult.value = "Fill in all the fields!"
        } else {

            val myToken = loginRepository.logIn(UserCredentials(username, password), object : TokenCallback {
                override fun onSuccess(token: Token) {
                    tokenResult.value = Token(token = token.token)
                }

                override fun onFail(throwable: Throwable) {
                    Log.e("Error", "$throwable")
                }
            })
        }
    }
}