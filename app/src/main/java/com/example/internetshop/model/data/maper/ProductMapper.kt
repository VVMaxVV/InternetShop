package com.example.internetshop.model.data.maper

import com.example.internetshop.Product
import com.example.internetshop.model.data.dataclass.ProductResponse
import javax.inject.Inject

class ProductMapper @Inject constructor() {
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
            it.rating.count,

        )
    }
}