package com.example.internetshop.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.internetshop.model.interfaces.LoginRepository
import com.example.internetshop.presentation.viewModel.AuthenticationActivityViewModel
import javax.inject.Inject


class ViewModelFactory @Inject constructor(private val loginRepository: LoginRepository) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when(modelClass){
            AuthenticationActivityViewModel::class.java->{
                return AuthenticationActivityViewModel(
                    loginRepository
                ) as T
            }
            else-> throw IllegalArgumentException("No such view model")
        }
    }
}