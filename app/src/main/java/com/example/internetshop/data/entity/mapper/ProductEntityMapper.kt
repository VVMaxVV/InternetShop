package com.example.internetshop.data.entity.mapper

import com.example.internetshop.data.entity.FavoriteProductEntity
import com.example.internetshop.domain.data.model.product.Product
import javax.inject.Inject

class ProductEntityMapper @Inject constructor() {
    fun toDomain(product: FavoriteProductEntity): Product {
        return Product(
            product.id.toLong(),
            product.imageURL,
            product.title,
            product.brand,
            "${product.price}$",
            product.shortDescription,
            product.description,
            product.rating,
            product.numberOfReviews.toString()
        )
    }

    fun toEntity(product: Product) : FavoriteProductEntity {
        return FavoriteProductEntity(
            product.id.toString(),
            product.imageURL,
            product.title,
            product.brand,
            "${product.price}$",
            product.shortDescription,
            product.description,
            product.rating.toFloat(),
            product.numberOfReviews.toInt()
        )
    }
}