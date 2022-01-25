package com.example.internetshop.presentation.adapters.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemSimpleProductBinding
import com.example.internetshop.domain.data.model.product.SimpleProduct
import com.squareup.picasso.Picasso

class SimpleProductViewHolder(private val binding: ItemSimpleProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(simpleProduct: SimpleProduct) {
        binding.price.text = simpleProduct.price
        Picasso.with(binding.root.context)
            .load(simpleProduct.imageUrl)
            .into(binding.productImage)
        binding.title.text = simpleProduct.title
        binding.colorValue.text = "Black"
        binding.sizeValue.text = "L"
        binding.price.text = simpleProduct.price + "$"
        binding.ratingBar.rating = simpleProduct.rating
        binding.numberOfReviews.text = "(${simpleProduct.numberOfReviews})"
        binding.itemSimpleProdId.id = simpleProduct.id.toInt()
    }
}