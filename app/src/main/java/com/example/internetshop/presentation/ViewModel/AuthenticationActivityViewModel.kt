package com.example.internetshop.presentation.ViewModel

import android.os.Handler
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internetshop.Token
import com.example.internetshop.model.interfaces.Auth

class AuthenticationActivityViewModel(private val auth: Auth):ViewModel() {
    val tokenResult = MutableLiveData<Token>()
    val textResult = MutableLiveData<String>()
    val progressBar = MutableLiveData<Int>()

    fun getToken(login: String, password: String) {
        if (login.isEmpty() || password.isEmpty()) {
            textResult.value = "Fill in all the fields!"
        } else {
            val handler = Handler()
            handler.postDelayed(Runnable {
                val myToken = auth.getAuthToken(login, password)
                if (myToken.tokenValue != "Wrong") {
                    tokenResult.value = myToken
                    textResult.value = "Successful login"
                }
                else textResult.value = "Wrong login or/and password"
            },1000)
            progressBar.value = View.VISIBLE

        }
    }
}