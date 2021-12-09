package com.example.internetshop.presentation.ViewModel

import android.os.Handler
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internetshop.model.implementation.AuthImpl

class AuthenticationActivityViewModel(private val auth: AuthImpl):ViewModel() {
    val tokenResult = MutableLiveData<com.example.internetshop.model.data.dataclass.Token>()
    val textResult = MutableLiveData<String>()
    val progressBar = MutableLiveData<Int>()

    fun getToken(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            textResult.value = "Fill in all the fields!"
        } else {
            val handler = Handler()
            handler.postDelayed(Runnable {
                val myToken = auth.getAuthToken(username, password)
                if (myToken.token != "Wrong") {
                    tokenResult.value = myToken
                    textResult.value = "Successful login"
                }
                else textResult.value = "Wrong login or/and password"
            },1000)
            progressBar.value = View.VISIBLE

        }
    }
}