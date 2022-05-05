package com.example.internetshop.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BagProducts")
data class BagProductEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val imageURL: String,
    val price: Float,
    val title: String,
    val color: String,
    val size: String,
    val amount: Int
)