package com.example.internetshop.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class TitleViewModel @Inject constructor() : BaseViewModel() {
    val titleLiveData = MutableLiveData<String>()
    val backArrowVisible = MutableLiveData<Boolean>()
    val isScrollingView = MutableLiveData<Boolean>()
}