package com.example.internetshop.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class TitleViewModel @Inject constructor() : BaseViewModel() {
    val title = MutableLiveData<String>()
    val backArrowVisible = MutableLiveData<Boolean>()
    val isScrollingView = MutableLiveData<Boolean>()
}