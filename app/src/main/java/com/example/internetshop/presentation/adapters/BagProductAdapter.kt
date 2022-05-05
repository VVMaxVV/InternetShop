package com.example.internetshop.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemBagProductBinding
import com.example.internetshop.model.data.viewStates.BagProductViewState
import com.example.internetshop.presentation.adapters.holder.BagProductViewHolder

class BagProductAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val productList = mutableListOf<BagProductViewState>()
    private var viewHolder: BagProductViewHolder? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        viewHolder = BagProductViewHolder(
            ItemBagProductBinding
                .inflate(LayoutInflater.from(parent.context), parent, false).apply {
                    lifecycleOwner = parent.findViewTreeLifecycleOwner()
                }
        )
        return viewHolder as BagProductViewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BagProductViewHolder).bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun add(products: List<BagProductViewState>) {
        productList.clear()
        productList.addAll(products)
        this.notifyDataSetChanged()
    }
}