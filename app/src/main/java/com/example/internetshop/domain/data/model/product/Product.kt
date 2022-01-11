package com.example.internetshop.domain.data.model.product

class Product(
    val id:Long,
    val imageURL:String,
    val title:String,
    val brand:String,
    val price:String,
    val shortDescription: String,
    val description: String,
    val rating: Float,
    val numberOfReviews: String
)