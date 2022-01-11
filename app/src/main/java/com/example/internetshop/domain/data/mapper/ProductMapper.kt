package com.example.internetshop.domain.data.mapper

import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.model.product.SimpleProduct
import javax.inject.Inject

class ProductMapper @Inject constructor() {
    fun toSimpleProduct(product: Product): SimpleProduct {
        return SimpleProduct(
            product.imageURL,
            product.title,
            "black",
            "L,XL",
            product.price,
            product.rating,
            product.numberOfReviews.toInt(),
            product.id.toString()
        )
    }
}