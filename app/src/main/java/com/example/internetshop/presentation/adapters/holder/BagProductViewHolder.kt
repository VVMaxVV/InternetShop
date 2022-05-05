package com.example.internetshop.presentation.adapters.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemBagProductBinding
import com.example.internetshop.model.data.viewStates.BagProductViewState

class BagProductViewHolder(private val binding: ItemBagProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        product: BagProductViewState
    ) {
        binding.product = product
        binding.brand.isSelected = true
    }


}