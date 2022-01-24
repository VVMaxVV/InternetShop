package com.example.internetshop.domain.data.mapper

import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.model.product.SimpleProduct
import javax.inject.Inject

class ProductMapper @Inject constructor() {
    fun toSimpleProduct(product: Product): SimpleProduct {
        var price = product.price
        if(price.length>2) {
            if(price[price.length-2]=='.') {
                price += "0"
            }
            if(price[price.length-3]!='.') {
                price += ".00"
            }
        }
        else {
            price += ".00"
        }

        return SimpleProduct(
            product.imageURL,
            product.title,
            "black",
            "L,XL",
            price,
            product.rating,
            product.numberOfReviews.toInt(),
            product.id.toString()
        )
    }
}