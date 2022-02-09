package com.example.internetshop.model.data.viewStates

import io.reactivex.subjects.PublishSubject

class ProductViewState(
    val imageUrl: String,
    val title: String,
    val price: String,
    val rating: Float,
    val numberOfReviews: String,
    val id: String
) : BaseViewState() {
    sealed class Event {
        data class OnClick(val id: String, val productName: String) : Event()
    }

    private val uiEvent = PublishSubject.create<Event>()
    val events = uiEvent.hide()
    fun onClick(id: String, productName: String) {
        uiEvent.onNext(Event.OnClick(id, productName))
    }
}