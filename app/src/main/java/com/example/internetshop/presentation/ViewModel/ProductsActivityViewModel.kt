package com.example.internetshop.presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internetshop.model.interfaces.ProductRepository
import com.example.internetshop.presentation.SimpleProduct
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductsActivityViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    val productsList = MutableLiveData<List<SimpleProduct>>()
    fun getProductsRx() {
        productRepository.getProductsRx()
            .subscribeOn(Schedulers.io())
            .map{
                it.map {
                    SimpleProduct(
                        it.imageURL,
                        it.title,
                        "Black",
                        "L",
                        "${it.prise}$",
                        it.rating,
                        it.numberOfReviews,
                        it.id.toString()
                    )
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                Consumer {
                    productsList.value = it
                }, Consumer {

                })
    }
}