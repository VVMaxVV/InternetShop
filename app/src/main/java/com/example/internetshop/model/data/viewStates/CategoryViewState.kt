package com.example.internetshop.model.data.viewStates

import io.reactivex.subjects.PublishSubject

class CategoryViewState(
    val name: String,
    val url: String
) {
    sealed class Event {
        data class OnClick(val name: String) : Event()
    }

    private val uiEvent = PublishSubject.create<Event>()
    val events = uiEvent.hide()
    fun onClick(name: String) {
        uiEvent.onNext(Event.OnClick(name))
    }
}