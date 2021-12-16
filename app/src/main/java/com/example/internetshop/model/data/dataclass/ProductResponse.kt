package com.example.internetshop.model.data.dataclass

data class ProductResponse(val id: Int,
                           val title: String,
                           val price: String,
                           val category: String,
                           val description: String,
                           val image: String,
                           val ratingResponse: RatingResponse)
