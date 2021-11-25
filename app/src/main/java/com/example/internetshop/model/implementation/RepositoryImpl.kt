package com.example.internetshop.model.implementation

import com.example.internetshop.Product
import com.example.internetshop.getProductFromServer
import com.example.internetshop.model.interfaces.Repository

class RepositoryImpl: Repository {
    override fun getProduct(): Product {
        val productResponse = getProductFromServer()
        return Product(
            productResponse.id,
            productResponse.title,
            productResponse.brand,
            String.format("%.2f",productResponse.prise),
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