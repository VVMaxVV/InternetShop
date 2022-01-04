package com.example.internetshop.model.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.internetshop.model.data.entity.FavoriteProductEntity

@Database(entities = [FavoriteProductEntity::class], version = 1)
abstract class InternetShopDB: RoomDatabase() {
    abstract fun
            favoriteProductsDao(): FavoriteProductsDao
}