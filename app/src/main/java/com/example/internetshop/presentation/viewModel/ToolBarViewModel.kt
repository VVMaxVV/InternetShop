package com.example.internetshop.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ToolBarViewModel @Inject constructor(): ViewModel() {
    val expanding = MutableLiveData<Boolean>()
}