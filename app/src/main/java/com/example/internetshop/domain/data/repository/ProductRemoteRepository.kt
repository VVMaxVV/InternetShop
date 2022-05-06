package com.example.internetshop.domain.data.repository

import com.example.internetshop.domain.data.model.product.Product
import io.reactivex.Single

interface ProductRemoteRepository {
    fun getProduct(id: String): Single<Product>
    fun getProducts(): Single<List<Product>>
}