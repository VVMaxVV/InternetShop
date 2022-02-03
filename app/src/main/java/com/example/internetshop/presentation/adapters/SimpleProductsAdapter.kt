package com.example.internetshop.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemSimpleProductBinding
import com.example.internetshop.domain.data.model.product.SimpleProduct
import com.example.internetshop.presentation.adapters.holder.SimpleProductViewHolder

class SimpleProductsAdapter(val clickListener: (SimpleProduct) -> Unit) :
    RecyclerView.Adapter<SimpleProductViewHolder>() {
    var productList: MutableList<SimpleProduct> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleProductViewHolder {
        return SimpleProductViewHolder(
            ItemSimpleProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SimpleProductViewHolder, position: Int) {
        holder.bind(productList[position])
        holder.itemView.setOnClickListener {
            clickListener(productList[position])
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun addData(simpleProducts: List<SimpleProduct>) {
        productList.clear()
        productList.addAll(simpleProducts)
        this.notifyDataSetChanged()
    }
}