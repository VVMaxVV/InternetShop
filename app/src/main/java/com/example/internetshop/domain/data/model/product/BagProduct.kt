package com.example.internetshop.domain.data.model.product

data class BagProduct(
    val id: Int,
    val imageURL: String,
    val price: Float,
    val title: String,
    val color: String,
    val size: String,
    val amount: Int
)