package com.example.internetshop.presentation.adapters.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.internetshop.databinding.ItemReviewBinding
import com.example.internetshop.domain.data.model.Review

class ReviewViewHolder(private val binding: ItemReviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(review: Review) {
        binding.userName.text = review.userName
        binding.data.text = review.date.getData()
        binding.rating.rating = review.rating.toFloat()
        binding.textReview.text = review.reviewText
    }}