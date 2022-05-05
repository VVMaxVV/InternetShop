package com.example.internetshop.model.data.viewStates

import androidx.lifecycle.MutableLiveData
import io.reactivex.subjects.PublishSubject

class BagProductViewState(
    val id: Int,
    val imageURL: String,
    val price: String,
    val title: String,
    val color: String,
    val size: String,
    var amount: MutableLiveData<Int>
) {
    sealed class Event {
        data class OnClick(val id: Int, val productName: String) : Event()
        data class OnPlusClick(val id: Int) : Event()
        data class OnMinusClick(val id: Int) : Event()
    }

    private val uiEvent = PublishSubject.create<Event>()
    val events = uiEvent.hide()
    fun onClick(id: Int, productName: String) {
        uiEvent.onNext(Event.OnClick(id, productName))
    }

    fun onPlus(id: Int) {
        uiEvent.onNext(Event.OnPlusClick(id))
    }

    fun onMinus(id: Int) {
        uiEvent.onNext(Event.OnMinusClick(id))
    }
}