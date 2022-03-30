package com.example.internetshop.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemCategoryBinding
import com.example.internetshop.databinding.ItemNotificationBinding
import com.example.internetshop.model.data.viewStates.BaseViewState
import com.example.internetshop.model.data.viewStates.CategoryViewState
import com.example.internetshop.model.data.viewStates.NotificationViewState
import com.example.internetshop.presentation.adapters.holder.CategoryViewHolder
import com.example.internetshop.presentation.adapters.holder.NotificationViewHolder
import javax.inject.Inject

private const val TYPE_CATEGORY = 1
private const val TYPE_NOTIFICATION = 2

class CategoryAdapter @Inject constructor() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val categoryList = mutableListOf<BaseViewState>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_CATEGORY -> CategoryViewHolder(
                ItemCategoryBinding.inflate(
                    LayoutInflater
                        .from(parent.context), parent, false
                )
            )
            else -> NotificationViewHolder(
                ItemNotificationBinding.inflate(
                    LayoutInflater
                        .from(parent.context), parent, false
                )
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

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun getSize() : Int {
        return categoryList.size
    }

    fun addData(categories: List<BaseViewState>) {
        categoryList.clear()
        categoryList.addAll(categories)
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_CATEGORY -> (holder as CategoryViewHolder).bind(
                categoryList[position] as CategoryViewState
            )
            TYPE_NOTIFICATION -> (holder as NotificationViewHolder).bind(
                categoryList[position] as NotificationViewState
            )
        }
    }
}