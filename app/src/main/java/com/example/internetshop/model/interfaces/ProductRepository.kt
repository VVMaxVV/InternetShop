package com.example.internetshop.model.interfaces

import com.example.internetshop.model.data.dataclass.ProductItem
import com.example.internetshop.model.data.dataclass.ProductListItems
import com.example.internetshop.model.data.dataclass.Token

interface ProductRepository {
    fun getProduct(id: String, productCallback: ProductCallback)
    fun getProductList(productListCallback: ProductListCallback)
}

interface LoginRepository {
    fun logIn(username: String, password: String, tokenCallback: TokenCallback)
}

interface TokenCallback {
    fun onSuccess(token: Token)
    fun onFail(throwable: Throwable)
}

interface ProductListCallback {
    fun onSuccess(product: List<ProductListItems>)
    fun onFail(throwable: Throwable)
}

interface ProductCallback {
    fun onSuccess(product: ProductItem)
    fun onFail(throwable: Throwable)
}