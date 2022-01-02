package com.example.internetshop.model.data.dataclass

import com.example.internetshop.model.data.maper.DataMapper

data class Review(val productId: String,
                  val userName: String,
                  val rating: Int,
                  val reviewText: String,
                  val date: DataMapper,
                  val userPhotoUrl: String)
