package com.example.internetshop.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.R
import com.example.internetshop.model.data.recyclerItem.CategoryItems
import com.example.internetshop.model.data.viewStates.BaseViewState
import com.example.internetshop.model.data.viewStates.CategoryViewState
import com.example.internetshop.model.data.viewStates.NotificationViewState
import com.example.internetshop.presentation.adapters.holder.CategoryViewHolder
import javax.inject.Inject

private const val TYPE_CATEGORY = 1
private const val TYPE_NOTIFICATION = 2

class CategoryAdapter @Inject constructor() :
    RecyclerView.Adapter<CategoryViewHolder>() {
    private val categoryList = mutableListOf<BaseViewState>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return when (viewType) {
            TYPE_CATEGORY -> CategoryViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_category, parent, false)
            )
            else -> CategoryViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_notification, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (categoryList[position]) {
            is CategoryViewState -> TYPE_CATEGORY
            is NotificationViewState -> TYPE_NOTIFICATION
            else -> 0
        }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_CATEGORY -> holder.bind(
                CategoryItems.Category(categoryList[position] as CategoryViewState)
            )
            TYPE_NOTIFICATION -> holder.bind(
                CategoryItems.Notification(categoryList[position] as NotificationViewState)
            )
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun addData(categories: List<BaseViewState>) {
        categoryList.clear()
        categoryList.addAll(categories)
        this.notifyDataSetChanged()
    }
}