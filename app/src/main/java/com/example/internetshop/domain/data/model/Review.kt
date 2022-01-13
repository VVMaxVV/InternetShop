package com.example.internetshop.domain.data.model

import com.example.internetshop.data.mock.DataMapper

data class Review(val productId: String,
                  val userName: String,
                  val rating: Int,
                  val reviewText: String,
                  val date: DataMapper,
                  val userPhotoUrl: String)
