package com.example.internetshop.model.data.viewStates

import com.example.internetshop.presentation.adapters.ItemInterface
import io.reactivex.subjects.PublishSubject

open class BaseViewState: ItemInterface {
    var type : Int? = null
    var id: Long? =  null

    override fun getItemType(): Int? = type

    override fun getID(): Long? = id

    sealed class Event {
        data class OnProductClick(val name: String) : Event()
        data class OnNotificationClick(val name: String) : Event()
    }

    private val uiEvent = PublishSubject.create<Event>()
    val events = uiEvent.hide()
    fun onProductClick(name: String) {
        uiEvent.onNext(Event.OnProductClick(name))
    }
    fun onNotificationClick(name: String) {
        uiEvent.onNext(Event.OnNotificationClick(name))
    }
}