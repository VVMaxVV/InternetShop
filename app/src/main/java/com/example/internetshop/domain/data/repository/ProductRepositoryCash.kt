package com.example.internetshop.domain.data.repository

import com.example.internetshop.domain.data.model.product.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductRepositoryCash {
    fun addToFavorite(product: Product): Completable
    fun deleteFromFavorite(product: Product): Completable
    fun getFavoriteProductList(): Single<List<Product>>
}