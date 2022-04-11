package com.example.internetshop.data.cache

import androidx.room.*
import com.example.internetshop.data.entity.FavoriteProductEntity
import io.reactivex.Completable

@Dao
interface FavoriteProductsDao {
    @Query("SELECT * FROM FavoriteProduct")
    fun getAllFromDB(): List<FavoriteProductEntity>

    @Query("SELECT * FROM FavoriteProduct WHERE id == (:id)")
    fun getProductByIdFromDB(id: Int): FavoriteProductEntity?

    @Query("SELECT * FROM FavoriteProduct ORDER BY title")
    fun getProductsByNameFromDB(): List<FavoriteProductEntity>

    @Query("SELECT * FROM FavoriteProduct ORDER BY title DESC")
    fun getProductsByNameFromDBDescending(): List<FavoriteProductEntity>

    @Query("SELECT * FROM FavoriteProduct ORDER BY rating")
    fun getProductByRatingFromDB(): List<FavoriteProductEntity>

    @Query("SELECT * FROM FavoriteProduct ORDER BY rating DESC")
    fun getProductByRatingFromDBDescending(): List<FavoriteProductEntity>

    @Query("SELECT * FROM FavoriteProduct ORDER BY CAST(price as integer)")
    fun getProductByPriceFromDB(): List<FavoriteProductEntity>

    @Query("SELECT * FROM FavoriteProduct ORDER BY CAST(price as integer) DESC")
    fun getProductByPriceFromDBDescending(): List<FavoriteProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDB(vararg product: FavoriteProductEntity): Completable

    @Delete
    fun deleteFromDB(productEntity: FavoriteProductEntity): Completable
}