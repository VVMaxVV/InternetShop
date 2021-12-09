package com.example.internetshop.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.internetshop.model.implementation.AuthImpl
import com.example.internetshop.presentation.ViewModel.AuthenticationActivityViewModel

class ViewModelFactory :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when(modelClass){
            AuthenticationActivityViewModel::class.java->{
                return AuthenticationActivityViewModel(
                    AuthImpl(username = "123", password = "123")
                ) as T
            }
            else-> throw IllegalArgumentException("No such view model")
        }
    }
}