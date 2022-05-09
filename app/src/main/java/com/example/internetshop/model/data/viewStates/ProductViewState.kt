package com.example.internetshop.model.data.viewStates

import androidx.lifecycle.MutableLiveData
import io.reactivex.subjects.PublishSubject

class ProductViewState(
    val imageUrl: String,
    val title: String,
    val price: String,
    val rating: Float,
    val numberOfReviews: String,
    val id: String,
    val favorite: MutableLiveData<Boolean>
) {
    sealed class Event {
        data class OnClick(val id: String, val productName: String) : Event()
        data class FavoriteToggleClick(val product: ProductViewState): Event()
    }

    private val uiEvent = PublishSubject.create<Event>()
    val events = uiEvent.hide()
    fun onClick(id: String, productName: String) {
        uiEvent.onNext(Event.OnClick(id, productName))
    }
    fun favoriteToggleClick(product: ProductViewState) {
        uiEvent.onNext(Event.FavoriteToggleClick(product))
    }
}