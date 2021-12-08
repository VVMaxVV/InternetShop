package com.example.internetshop.model.interfaces

import com.example.internetshop.Product
import com.example.internetshop.model.data.remote.ProductListItems

interface Repository {
    fun getProduct(): Product
    fun getProductList(productListCallback: ProductListCallback)
}

interface ProductListCallback {
    fun onSucses(product: List<ProductListItems>)
    fun onFail(throwable: Throwable)
}