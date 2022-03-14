package com.example.internetshop.model.data.recyclerItem

import com.example.internetshop.model.data.viewStates.CategoryViewState
import com.example.internetshop.model.data.viewStates.NotificationViewState

sealed class CategoryItems {
    data class Category(val categoryViewState: CategoryViewState): CategoryItems()
    data class Notification(val notificationViewState: NotificationViewState): CategoryItems()
}
