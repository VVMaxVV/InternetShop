package com.example.internetshop.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemCategoryBinding
import com.example.internetshop.domain.data.model.Category
import com.example.internetshop.presentation.adapters.holder.CategoryViewHolder

class CategoryAdapter(val clickListener: (Category) -> Unit) : RecyclerView.Adapter<CategoryViewHolder>() {
    private val categoryList = mutableListOf<Category>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
        holder.itemView.setOnClickListener {
            clickListener(categoryList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun addData(category: Category) {
        categoryList.add(category)
    }
}