package com.example.internetshop.data.entity.mapper

import com.example.internetshop.data.entity.BagProductEntity
import com.example.internetshop.domain.data.model.product.BagProduct
import javax.inject.Inject

class BagEntityMapper @Inject constructor() {
    fun toDomain(product: BagProductEntity) = BagProduct(
        product.id,
        product.imageURL,
        product.price,
        product.title,
        product.color,
        product.size,
        product.amount
    )

    fun toEntity(product: BagProduct) = BagProductEntity(
        product.id,
        product.imageURL,
        product.price,
        product.title,
        product.color,
        product.size,
        product.amount
    )
}