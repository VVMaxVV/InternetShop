package com.example.internetshop.data.response.mapper

import com.example.internetshop.data.response.ProductResponse
import com.example.internetshop.domain.data.model.product.Product
import javax.inject.Inject

class ProductServerMapper @Inject constructor() {
    fun toDomain(it: ProductResponse): Product {
        return Product(
            it.id.toLong(),
            it.image,
            it.title,
            it.title,
            it.price,
            "",
            it.description,
            it.rating.rate.toFloat(),
            it.rating.count.toString(),
        )
    }
}