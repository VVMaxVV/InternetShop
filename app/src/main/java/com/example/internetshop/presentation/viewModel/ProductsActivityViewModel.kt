package com.example.internetshop.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internetshop.domain.data.model.product.SimpleProduct
import com.example.internetshop.domain.data.repository.ProductRepository
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductsActivityViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    val productsList = MutableLiveData<List<SimpleProduct>>()
    val openDetailsEvent = SingleLiveEvent<String>()

    fun onProductClicked(product: SimpleProduct) {
        openDetailsEvent.value = product.id
    }

    fun getProductsRx() {
        productRepository.getProductsRx()
            .subscribeOn(Schedulers.io())
            .map {
                it.map {
                    SimpleProduct(
                        it.imageURL,
                        it.title,
                        "Black",
                        "L",
                        it.price,
                        it.rating.toFloat(),
                        it.numberOfReviews.toInt(),
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