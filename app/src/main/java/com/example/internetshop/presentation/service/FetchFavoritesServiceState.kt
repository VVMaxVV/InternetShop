package com.example.internetshop.presentation.service

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchFavoritesServiceState @Inject constructor() {
    val event = MutableLiveData<Event>()

    sealed class Event {
        object ServiceDestroying : Event()
    }
}