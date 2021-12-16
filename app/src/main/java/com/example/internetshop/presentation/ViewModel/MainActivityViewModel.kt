package com.example.internetshop.presentation.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internetshop.Product
import com.example.internetshop.model.data.dataclass.ProductItem
import com.example.internetshop.model.data.dataclass.ProductListItems
import com.example.internetshop.model.interfaces.ProductCallback
import com.example.internetshop.model.interfaces.ProductListCallback
import com.example.internetshop.model.interfaces.ProductRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val productRepository: ProductRepository):
    ViewModel() {
    val productLiveData = MutableLiveData<Product>()
    val productsLifeData = MutableLiveData<List<ProductListItems>>()


    fun getProduct() {
        val myProduct = productRepository.getProduct("1", object : ProductCallback {
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
        val myProducts = productRepository.getProductList(object : ProductListCallback {
            override fun onSuccess(product: List<ProductListItems>) {
                Log.i("API123","${product.toString()}")
            }

            override fun onFail(throwable: Throwable) {
                Log.i("API123","$throwable")
            }

        })
    }

    fun getProductRx(id: String) {
        productRepository.getProductRx(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : SingleObserver<Product> {
                override fun onSubscribe(d: Disposable) {
                    println()
                }

                override fun onSuccess(t: Product) {
                    productLiveData.value = t
                }
                override fun onError(e: Throwable) {
                    Log.e("Test", "${e.message}")
                }
            })
    }

    fun getProductsRx() {
        productRepository.getProductsRx()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}