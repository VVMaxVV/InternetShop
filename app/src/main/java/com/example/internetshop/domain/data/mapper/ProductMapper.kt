package com.example.internetshop.domain.data.mapper

import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.model.product.SimpleProduct
import java.util.*
import javax.inject.Inject

class ProductMapper @Inject constructor() {
    fun toSimpleProduct(product: Product): SimpleProduct {
        return SimpleProduct(
            product.imageURL,
            product.title,
            "black",
            "L,XL",
            String.format(Locale.ROOT, "%.2f", product.price.toFloat()),
            product.rating,
            product.numberOfReviews.toInt(),
            product.id.toString()
        )
    }
}