package com.example.internetshop.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemSimpleProductBinding
import com.example.internetshop.presentation.SimpleProduct
import com.squareup.picasso.Picasso

class SimpleProductsAdapter : RecyclerView.Adapter<SimpleProductViewHolder>() {
    var productList: MutableList<SimpleProduct> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleProductViewHolder {
        return SimpleProductViewHolder(ItemSimpleProductBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SimpleProductViewHolder, position: Int) {
        holder.bind(productList.get(position))
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}

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
        binding.price.text = simpleProduct.price
        binding.ratingBar.rating = simpleProduct.rating
        binding.numberOfReviews.text = "(${simpleProduct.numberOfReviews})"
    }
}