package com.example.internetshop.presentation.adapters.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemCategoryBinding
import com.example.internetshop.databinding.ItemNotificationBinding
import com.example.internetshop.model.data.recyclerItem.CategoryItems

class CategoryViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    fun bind(
        categoryItems: CategoryItems
    ) {
        when (categoryItems) {
            is CategoryItems.Category -> ItemCategoryBinding.bind(itemView.rootView).data =
                categoryItems.categoryViewState
            is CategoryItems.Notification -> ItemNotificationBinding.bind(itemView.rootView).data =
                categoryItems.notificationViewState
        }
    }
}