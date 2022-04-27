package com.example.internetshop.domain.data.repository

import com.example.internetshop.domain.data.model.product.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductRepositoryCash {
    fun addToFavorite(product: Product): Completable
    fun deleteFromFavorite(product: Product): Completable
    fun updateProductDate(product: Product): Completable
    fun updateProductsDate(productList: List<Product>): Completable
    fun getFavoriteProductList(): Single<List<Product>>
    fun getFavoriteProductListDescending(): Single<List<Product>>
    fun getFavoriteProductById(id: String): Single<Product>
    fun getFavoriteProductByName(): Single<List<Product>>
    fun getFavoriteProductByNameDescending(): Single<List<Product>>
    fun getFavoriteProductByRating(): Single<List<Product>>
    fun getFavoriteProductByRatingDescending(): Single<List<Product>>
    fun getFavoriteProductByPrice(): Single<List<Product>>
    fun getFavoriteProductByPriceDescending(): Single<List<Product>>
    fun isProductInDB(product: Product): Single<Boolean>
    fun getIdAllProduct(): Single<List<String>>
}