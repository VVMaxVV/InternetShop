package com.example.internetshop.presentation.adapters.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemCategoryBinding
import com.example.internetshop.model.data.viewStates.CategoryViewState

class CategoryViewHolder(private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        category: CategoryViewState
    ) {
        binding.data = category
    }
}