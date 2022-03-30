package com.example.internetshop.presentation.adapters.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemNotificationBinding
import com.example.internetshop.model.data.viewStates.NotificationViewState

class NotificationViewHolder(private val binding: ItemNotificationBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        notificationViewState: NotificationViewState
    ) {
        binding.data = notificationViewState
    }
}