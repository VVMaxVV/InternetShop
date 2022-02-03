package com.example.internetshop.presentation.adapters.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemProductCategoryBinding
import com.example.internetshop.model.data.viewStates.ProductViewState

class ProductViewHolder(private val binding: ItemProductCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(productViewState: ProductViewState) {
        binding.product = productViewState
    }
}