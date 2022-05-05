package com.example.internetshop.data.di.module

import android.content.Context
import androidx.room.Room
import com.example.internetshop.data.cache.BagProductDao
import com.example.internetshop.data.cache.FavoriteProductsDao
import com.example.internetshop.data.cache.InternetShopDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {
    @Provides
    @Singleton
    fun getDB(applicationContext: Context): InternetShopDB {
        return Room.databaseBuilder(
            applicationContext,
            InternetShopDB::class.java,
            "ApplicationDB"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun getDao(internetShopDB: InternetShopDB): FavoriteProductsDao {
        return internetShopDB.favoriteProductsDao()
    }

    @Provides
    @Singleton
    fun getCartProductDao(internetShopDB: InternetShopDB): BagProductDao {
        return internetShopDB.cartProductsDao()
    }
}