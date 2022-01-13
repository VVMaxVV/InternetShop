package com.example.internetshop.domain.data.repository

import com.example.internetshop.domain.data.model.product.Product
import io.reactivex.Single

interface ProductRepository {
    fun getProductRx(id: String): Single<Product>
    fun getProductsRx(): Single<List<Product>>
}