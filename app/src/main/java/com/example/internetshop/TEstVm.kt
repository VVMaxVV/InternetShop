package com.example.internetshop

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TEstVm(val context:Context): ViewModel() {

    val navigationEvents :MutableLiveData<String> = MutableLiveData()

    fun login(){
        //model.login()
        navigationEvents.value = "user_authorized_token_ajksdkajdskajskdj"
    }



}