package com.example.internetshop.data.cache

import androidx.room.*
import com.example.internetshop.data.entity.FavoriteProductEntity
import io.reactivex.Completable

@Dao
interface FavoriteProductsDao {
    @Query("SELECT * FROM FavoriteProduct")
    fun getAllFromDB(): List<FavoriteProductEntity>

    @Query("SELECT * FROM FavoriteProduct WHERE id == (:id)")
    fun getProductByIdFromDB(id: Int): FavoriteProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDB(vararg product: FavoriteProductEntity): Completable

    @Delete
    fun deleteFromDB(product: FavoriteProductEntity)

}