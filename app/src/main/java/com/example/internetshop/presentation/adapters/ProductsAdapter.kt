package com.example.internetshop.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemProductCategoryBinding
import com.example.internetshop.model.data.viewStates.ProductViewState
import com.example.internetshop.presentation.adapters.holder.ProductViewHolder
import javax.inject.Inject


class ProductsAdapter @Inject constructor() :
    RecyclerView.Adapter<ProductViewHolder>() {
    private val productList: MutableList<ProductViewState> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).apply {
                lifecycleOwner = parent.findViewTreeLifecycleOwner()
            }
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    fun addData(productViewStateList: List<ProductViewState>) {
        productList.clear()
        productList.addAll(productViewStateList)
        this.notifyDataSetChanged()
    }
}