package com.example.internetshop.presentation.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.Product
import com.example.internetshop.model.data.dataclass.ProductItem
import com.example.internetshop.model.data.dataclass.ProductListItems
import com.example.internetshop.model.interfaces.ProductCallback
import com.example.internetshop.model.interfaces.ProductListCallback
import com.example.internetshop.model.interfaces.Repository

class MainActivityViewModel(private val repository: Repository) {
    val productLiveData = MutableLiveData<Product>()
    val productsLifeData = MutableLiveData<List<ProductListItems>>()


    fun getProduct() {
        val myProduct = repository.getProduct("1", object : ProductCallback {
            override fun onSuccess(product: ProductItem) {
                productLiveData.value = Product(
                    id = product.id.toLong(),
                    title = product.title,
                    brand = product.title,
                    prise = product.price,
                    "",
                    description = product.description,
                    rating = 4.0f,
                    numberOfReviews = 10
                )
            }

            override fun onFail(throwable: Throwable) {
                Log.e("Error", "$throwable")
            }
        })
    }

    fun getProducts() {
        val myProducts = repository.getProductList(object : ProductListCallback {
            override fun onSuccess(product: List<ProductListItems>) {
                Log.i("API123","${product.toString()}")
            }

            override fun onFail(throwable: Throwable) {
                Log.i("API123","$throwable")
            }

        })
    }
}