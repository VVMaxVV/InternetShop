package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internetshop.Product
import com.example.internetshop.model.interfaces.ProductRepository
import com.example.internetshop.presentation.SingleLiveEvent
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(private val productRepository: ProductRepository):
    ViewModel() {
    val productLiveData = MutableLiveData<Product>()
    val toastEventLiveData = SingleLiveEvent<String>()
    private val compositeDisposable = CompositeDisposable()

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
        productRepository.addToFavorite(productLiveData.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {

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
        productRepository.getFavoriteProductList()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}