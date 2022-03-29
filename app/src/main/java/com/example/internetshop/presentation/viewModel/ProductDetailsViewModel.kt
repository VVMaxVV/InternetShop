package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductRepository
import com.example.internetshop.domain.data.repository.ProductRepositoryCash
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val productRepositoryCash: ProductRepositoryCash
) :
    BaseViewModel() {
    val event = SingleLiveEvent<ProductDetailsEvent>()
    val productLiveData = MutableLiveData<Product>()
    val toastEventLiveData = SingleLiveEvent<String>()
    val favoriteProductsLiveData = MutableLiveData<List<Product>>()
    var favorite = false

    sealed class ProductDetailsEvent {
        data class OpenReview(val id: String) : ProductDetailsEvent()
        data class AddToFavorite(val value: Boolean) : ProductDetailsEvent()
    }

    fun getProductRx(id: String) {
        productRepository.getProductRx(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                productLiveData.value = it
            }, {
                Log.i(ProductDetailsViewModel::class.java.name, "Error: ${it.message}")
            }).run(compositeDisposable::add)
//            .subscribeWith(object : SingleObserver<Product> {
//                override fun onSubscribe(d: Disposable) {
//                    compositeDisposable.add(d)
//                }
//
//                override fun onSuccess(t: Product) {
//                    productLiveData.value = t
//                }
//
//                override fun onError(e: Throwable) {
//                    Log.e("Test", "${e.message}")
//                }
//            })
    }

    fun addToFavorite() {
        productRepositoryCash.addToFavorite(
            productLiveData.value ?: Product(
                0,
                "N/A",
                "N/A",
                "N/A",
                "N/A",
                "N/A",
                "N/A",
                0f,
                "N/A"
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                toastEventLiveData.value = "Date in db"
            }, {
                Log.i(ProductDetailsViewModel::class.java.name, "Error: ${it.message}")
            }).run(compositeDisposable::add)
//            .subscribeWith(object : CompletableObserver {
//                override fun onSubscribe(d: Disposable) {
//                    compositeDisposable.add(d)
//                }
//
//                override fun onComplete() {
//                    toastEventLiveData.value = "Date in db"
//                }
//
//                override fun onError(e: Throwable) {
//                    toastEventLiveData.value = "Error: ${e.message}"
//                }
//            })
    }

    fun deleteFromeFavorite() {
        if (productLiveData.value != null) {
                productRepositoryCash.addToFavorite(productLiveData.value!!)
            }
        else FavoriteListViewModel.Event.ToastEvent("Error: Product not found")
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