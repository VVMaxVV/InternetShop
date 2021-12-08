package com.example.internetshop.presentation.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.Product
import com.example.internetshop.model.data.remote.ProductListItems
import com.example.internetshop.model.interfaces.ProductListCallback
import com.example.internetshop.model.interfaces.Repository

class MainActivityViewModel(private val repository: Repository) {
    val productLiveData = MutableLiveData<Product>()
    val productsLifeData = MutableLiveData<List<ProductListItems>>()


    fun getProduct() {
//        val myProduct = repository.getProduct()
//        productLiveData.value = myProduct
    }

    fun getProducts() {
        val myProducts = repository.getProductList(object : ProductListCallback {
            override fun onSucses(product: List<ProductListItems>) {
                Log.i("API123","${product.toString()}")
            }

            override fun onFail(throwable: Throwable) {
                Log.i("API123","$throwable")
            }

        })
    }
}