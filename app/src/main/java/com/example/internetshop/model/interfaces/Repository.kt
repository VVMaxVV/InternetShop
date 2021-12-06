package com.example.internetshop.model.interfaces

import com.example.internetshop.Product

interface Repository {
    fun getProduct(): Product
    fun getProductList() :List<Product>
}