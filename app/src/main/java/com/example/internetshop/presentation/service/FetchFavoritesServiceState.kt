package com.example.internetshop.presentation.service

import com.example.internetshop.presentation.utils.SingleLiveEvent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchFavoritesServiceState @Inject constructor() {
    val events = SingleLiveEvent<Event>()

    sealed class Event {
        object ServiceDestroying : Event()
    }
}