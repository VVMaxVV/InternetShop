package com.example.internetshop.domain.data.repository

import com.example.internetshop.domain.data.model.product.BagProduct
import io.reactivex.Completable
import io.reactivex.Single

interface BagRepository {
    fun getProduct(id: String): Single<BagProduct>
    fun getProducts(): Single<List<BagProduct>>
    fun add(product: BagProduct): Completable
    fun add(productsList: List<BagProduct>): Completable
    fun delete(product: BagProduct): Completable
    fun delete(productList: List<BagProduct>): Completable
    fun update(product: BagProduct): Completable
    fun update(productList: List<BagProduct>): Completable
}