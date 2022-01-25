package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductRepository
import com.example.internetshop.domain.data.repository.ProductRepositoryCash
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val productRepositoryCash: ProductRepositoryCash
) :
    BaseViewModel() {
    val productLiveData = MutableLiveData<Product>()
    val toastEventLiveData = SingleLiveEvent<String>()
    val favoriteProductsLiveData = MutableLiveData<List<Product>>()

    fun getProductRx(id: String) {
        productRepository.getProductRx(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : SingleObserver<Product> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: Product) {
                    productLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.e("Test", "${e.message}")
                }
            })
    }

    fun addToFavorite() {
        productRepositoryCash.addToFavorite(productLiveData.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    toastEventLiveData.value = "Date in db"
                }

                override fun onError(e: Throwable) {
                    toastEventLiveData.value = "Error: ${e.message}"
                }
            })
    }

    fun getFromFavorite() {
        productRepositoryCash.getFavoriteProductList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                favoriteProductsLiveData.value = it
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}