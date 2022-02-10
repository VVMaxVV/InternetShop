package com.example.internetshop.model.data.viewStates

import io.reactivex.subjects.PublishSubject

class ProductViewState(
    val imageUrl: String,
    val title: String,
    val price: String,
    val rating: Float,
    val numberOfReviews: String,
    val id: String
) {
    sealed class Event {
        data class OnClick(val id: String) : Event()
    }

    private val uiEvent = PublishSubject.create<Event>()
    val events = uiEvent.hide()
    fun onClick(id: String) {
        uiEvent.onNext(Event.OnClick(id))
    }
}