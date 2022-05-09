package com.example.internetshop.data.cache

import androidx.room.*
import com.example.internetshop.data.entity.BagProductEntity
import io.reactivex.Completable

@Dao
interface BagProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(product: BagProductEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(productList: List<BagProductEntity>): Completable

    @Delete
    fun delete(product: BagProductEntity): Completable

    @Delete
    fun delete(productList: List<BagProductEntity>): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(product: BagProductEntity) : Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(productList: List<BagProductEntity>): Completable

    @Query("SELECT * FROM BagProducts")
    fun get(): List<BagProductEntity>

    @Query("SELECT * FROM BagProducts WHERE id == (:id)")
    fun get(id: Int): BagProductEntity
}