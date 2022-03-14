package com.example.internetshop.model.data.factory

import com.example.internetshop.model.data.viewStates.NotificationViewState

class NotificationCategoryFactory {
    fun create() : NotificationViewState {
        return NotificationViewState("SAIL","50%")
    }
}