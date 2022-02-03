package com.example.internetshop.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemCategoryBinding
import com.example.internetshop.model.data.viewStates.CategoryViewState
import com.example.internetshop.presentation.adapters.holder.CategoryViewHolder
import javax.inject.Inject

class CategoryAdapter @Inject constructor():
    RecyclerView.Adapter<CategoryViewHolder>() {
    private val categoryList = mutableListOf<CategoryViewState>()
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
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun addData(categories: List<CategoryViewState>) {
        categoryList.clear()
        categoryList.addAll(categories)
        this.notifyDataSetChanged()
    }
}