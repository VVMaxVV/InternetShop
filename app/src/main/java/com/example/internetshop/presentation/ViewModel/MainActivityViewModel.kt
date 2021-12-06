package com.example.internetshop.presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import com.example.internetshop.Product
import com.example.internetshop.model.interfaces.Repository

class MainActivityViewModel(private val repository: Repository) {
    val productLiveData = MutableLiveData<Product>()
    val productsLifeData = MutableLiveData<List<Product>>()


    fun getProduct() {
        val myProduct = repository.getProduct()
        productLiveData.value = myProduct
    }

    fun getProducts() {
        val myProducts = repository.getProductList()
        productsLifeData.value = myProducts
    }
}