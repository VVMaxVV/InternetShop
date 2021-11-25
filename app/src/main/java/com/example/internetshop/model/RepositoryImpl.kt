package com.example.internetshop.model

import com.example.internetshop.Product
import com.example.internetshop.getProductFromServer

class RepositoryImpl: Repository {
    override fun getProduct(): Product {
        val productResponse = getProductFromServer()
        return Product(
            productResponse.id,
            productResponse.title,
            productResponse.brand,
            productResponse.prise.toString(),
            productResponse.shortDescription,
            productResponse.description,
            productResponse.rating,
            productResponse.numberOfReviews
        )
    }

    override fun getProductList(): List<Product> {
        TODO("Not yet implemented")
    }
}