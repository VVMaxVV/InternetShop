package com.example.internetshop.presentation.adapters.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemCategoryBinding
import com.example.internetshop.model.data.viewStates.CategoryViewState

class CategoryErrorViewHolder(private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        categoryViewState: CategoryViewState
    ) {
        binding.data = categoryViewState
    }
}