package com.example.internetshop.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class BottomNavViewModel @Inject constructor(): BaseViewModel() {
    val bottomNavVisibility = MutableLiveData<Boolean>()
}