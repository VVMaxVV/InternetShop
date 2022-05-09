package com.example.internetshop.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.internetshop.data.entity.BagProductEntity
import com.example.internetshop.data.entity.FavoriteProductEntity

@Database(entities = [FavoriteProductEntity::class, BagProductEntity::class], version = 1)
abstract class InternetShopDB : RoomDatabase() {
    abstract fun favoriteProductsDao(): FavoriteProductsDao
    abstract fun cartProductsDao(): BagProductDao
}