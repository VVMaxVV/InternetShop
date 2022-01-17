package com.example.internetshop.presentation.adapters.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemCategoryBinding
import com.example.internetshop.domain.data.model.Category
import com.squareup.picasso.Picasso

class CategoryViewHolder(private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.data = category
            Picasso.with(binding.root.context)
                .load(category.imageUri)
                .into(binding.imageviewCategoryImage)
        }
}