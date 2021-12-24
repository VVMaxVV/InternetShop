package com.example.internetshop.model.data.dataclass

data class Review(val productId: String,
                  val userName: String,
                  val rating: Int,
                  val reviewText: String,
                  val date: String,
                  val userPhotoUrl: String)
