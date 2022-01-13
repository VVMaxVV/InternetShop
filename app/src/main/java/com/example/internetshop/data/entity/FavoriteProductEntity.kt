package com.example.internetshop.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteProduct")
data class FavoriteProductEntity (
    @PrimaryKey(autoGenerate = false) val id:String,
    val imageURL:String,
    val title:String,
    val brand:String,
    val price:String,
    val shortDescription: String,
    val description: String,
    val rating: Float,
    val numberOfReviews: Int
)