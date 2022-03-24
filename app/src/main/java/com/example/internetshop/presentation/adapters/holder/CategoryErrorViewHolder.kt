package com.example.internetshop.presentation.adapters.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemCategoryErrorBinding
import com.example.internetshop.model.data.viewStates.ErrorViewState

class CategoryErrorViewHolder(private val binding: ItemCategoryErrorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        errorViewState: ErrorViewState
    ) {
        binding.data = errorViewState
    }
}