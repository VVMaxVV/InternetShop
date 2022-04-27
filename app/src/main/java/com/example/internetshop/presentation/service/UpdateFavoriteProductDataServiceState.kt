package com.example.internetshop.presentation.service

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateFavoriteProductDataServiceState @Inject constructor() {
    val event = MutableLiveData<UpdateFavoriteProductDataServiceEvent>()

    sealed class UpdateFavoriteProductDataServiceEvent {
        object ServiceDestroying : UpdateFavoriteProductDataServiceEvent()
    }
}