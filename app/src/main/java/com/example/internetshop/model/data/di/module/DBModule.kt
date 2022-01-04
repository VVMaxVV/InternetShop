package com.example.internetshop.model.data.di.module

import android.content.Context
import androidx.room.Room
import com.example.internetshop.model.data.FavoriteProductsDao
import com.example.internetshop.model.data.InternetShopDB
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
        ).build()
    }

    @Provides
    @Singleton
    fun getDao(internetShopDB: InternetShopDB): FavoriteProductsDao {
        return internetShopDB.favoriteProductsDao()
    }
}