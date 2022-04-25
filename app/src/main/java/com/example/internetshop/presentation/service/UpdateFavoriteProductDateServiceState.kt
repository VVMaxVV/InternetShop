package com.example.internetshop.presentation.service

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateFavoriteProductDateServiceState @Inject constructor() {
    val serviceDestroy = MutableLiveData<Boolean>()
    val stopService = MutableLiveData<Boolean>()
}