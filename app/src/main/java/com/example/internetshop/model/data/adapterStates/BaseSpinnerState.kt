package com.example.internetshop.model.data.adapterStates

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class BaseSpinnerState @Inject constructor() {
    val position = MutableLiveData<Int?>()
    val positionValue = MutableLiveData<String?>()
    val list = MutableLiveData<List<String>>()
}