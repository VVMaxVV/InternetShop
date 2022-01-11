package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.model.Token
import com.example.internetshop.domain.data.model.UserCredentials
import com.example.internetshop.domain.data.repository.LoginRepository
import com.example.internetshop.model.interfaces.TokenCallback
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    BaseViewModel() {
    val tokenResult = MutableLiveData<Token>()
    val textResult = MutableLiveData<String>()

    val password = ObservableField<String>()
    val username = ObservableField<String>()

    fun getToken() {
        if (username.get().isNullOrEmpty().not() || password.get().isNullOrEmpty().not()) {
            textResult.value = "Fill in all the fields!"
        } else {

            val myToken = loginRepository.logIn(UserCredentials(username.get()!!, password.get()!!), object : TokenCallback {
                override fun onSuccess(token: Token) {
                    tokenResult.value = Token(token.token)
                }
                override fun onFail(throwable: Throwable) {
                    Log.e("Error", "$throwable")
                    textResult.value = throwable.message
                }
            })
        }
    }
}