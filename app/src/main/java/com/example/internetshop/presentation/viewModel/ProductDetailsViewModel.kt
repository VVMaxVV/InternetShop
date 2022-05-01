package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductLocalRepository
import com.example.internetshop.domain.data.repository.ProductRemoteRepository
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ProductDetailsViewModel @Inject constructor(
    private val productRemoteRepository: ProductRemoteRepository,
    private val productLocalRepository: ProductLocalRepository
) :
    BaseViewModel() {
    val event = SingleLiveEvent<ProductDetailsEvent>()
    val productLiveData = MutableLiveData<Product>()
    val toastEventLiveData = SingleLiveEvent<String>()
    val favoriteProductsLiveData = MutableLiveData<List<Product>>()
    var favoriteIsChecked = MutableLiveData(false)

    sealed class ProductDetailsEvent {
        data class OpenReview(val id: String) : ProductDetailsEvent()
        data class AddToFavorite(val value: Boolean) : ProductDetailsEvent()
        data class ShowToast(val text: String) : ProductDetailsEvent()
        object ProductNotFound : ProductDetailsEvent()
    }

    private fun isInDB() {
        productLocalRepository.isProductInDB(productLiveData.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                favoriteIsChecked.value = it
            }, {
                ProductDetailsEvent.ShowToast(it.message ?: "Unknown error")
            }).run(compositeDisposable::add)
    }

    fun getProductRx(id: String) {
        productRemoteRepository.getProduct(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                productLiveData.value = it
                isInDB()
            }, {
                Log.i(ProductDetailsViewModel::class.java.name, "Error: ${it.message}")
            }).run(compositeDisposable::add)
    }

    fun favoriteClicked() {
        if (favoriteIsChecked.value == true) deleteFromFavorite()
        else addToFavorite()
        favoriteIsChecked.value = favoriteIsChecked.value?.not()
    }

    fun addToFavorite() {
        if (productLiveData.value != null) {
            productLocalRepository.addToFavorite(productLiveData.value!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        } else {
            event.value = ProductDetailsEvent.ProductNotFound
            favoriteIsChecked.value = false
        }
    }

    fun deleteFromFavorite() {
        if (productLiveData.value != null) {
            productLocalRepository.deleteFromFavorite(productLiveData.value!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        } else ProductDetailsEvent.ProductNotFound
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}