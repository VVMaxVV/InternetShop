package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.model.product.BagProduct
import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductLocalRepository
import com.example.internetshop.domain.data.repository.ProductRemoteRepository
import com.example.internetshop.domain.data.usecase.AddProductToBagUseCase
import com.example.internetshop.domain.data.usecase.GetProductColorsUseCase
import com.example.internetshop.domain.data.usecase.GetProductSizesUseCase
import com.example.internetshop.model.data.adapterStates.BaseSpinnerState
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ProductDetailsViewModel @Inject constructor(
    private val productRemoteRepository: ProductRemoteRepository,
    private val productLocalRepository: ProductLocalRepository,
    private val getProductSizesUseCase: GetProductSizesUseCase,
    private val getProductColorsUseCase: GetProductColorsUseCase,
    private val addProductToBagUseCase: AddProductToBagUseCase,
    val sizesSpinnerState: BaseSpinnerState,
    val colorsSpinnerState: BaseSpinnerState
) :
    BaseViewModel() {
    val event = SingleLiveEvent<Event>()
    val product = MutableLiveData<Product>()
    val toastEventLiveData = SingleLiveEvent<String>()
    val favoriteProductsLiveData = MutableLiveData<List<Product>>()
    var favoriteIsChecked = MutableLiveData(false)

    sealed class Event {
        data class OpenReview(val id: String) : Event()
        data class AddToFavorite(val value: Boolean) : Event()
        data class ShowToast(val text: String) : Event()
        data class ReceiveThrowable(val throwable: Throwable, val message: String? = null) : Event()
        object ProductNotFound : Event()
        object NotAllFieldsAreFilled : Event()
        object AddedToCard : Event()
    }

    private fun isInDB(id: String) {
        productLocalRepository.isProductInDB(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                favoriteIsChecked.value = it
            }, {
                event.value = Event.ReceiveThrowable(it, "isProductInDB()")
            }).run(compositeDisposable::add)
    }

    fun getProduct(id: String) {
        productRemoteRepository.getProduct(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                product.value = it
                isInDB(it.id.toString())
            }, {
                event.value = Event.ReceiveThrowable(it)
            }).run(compositeDisposable::add)
    }

    fun favoriteClicked() {
        if (favoriteIsChecked.value == true) deleteFromFavorite()
        else addToFavorite()
        favoriteIsChecked.value = favoriteIsChecked.value?.not()
    }

    fun addToFavorite() {
        if (product.value != null) {
            productLocalRepository.addToFavorite(product.value!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        } else {
            event.value = Event.ProductNotFound
            favoriteIsChecked.value = false
        }
    }

    fun deleteFromFavorite() {
        if (product.value != null) {
            productLocalRepository.deleteFromFavorite(product.value!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        } else event.value = Event.ProductNotFound
    }

    fun getSpinnerEntries() {
        getProductSizesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                sizesSpinnerState.list.value = it
            }, {
                Log.e(
                    ProductDetailsViewModel::class.java.name,
                    "Product Sizes error: ${it.message}"
                )
            }).run(compositeDisposable::add)
        getProductColorsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                colorsSpinnerState.list.value = it
            }, {
                Log.e(
                    ProductDetailsViewModel::class.java.name,
                    "Product Colors error: ${it.message}"
                )
            }).run(compositeDisposable::add)
    }

    fun addToCart() {
        if (sizesSpinnerState.position.value == 0 || colorsSpinnerState.position.value == 0) {
            event.value = Event.NotAllFieldsAreFilled
        } else {
            product.value?.apply {
                addProductToBagUseCase.execute(
                    colorsSpinnerState.positionValue.value?.let { color ->
                        sizesSpinnerState.positionValue.value?.let { size ->
                            BagProduct(
                                id.toInt(),
                                imageURL,
                                price.toFloat(),
                                title,
                                color,
                                size,
                                1
                            )
                        }
                    }
                ).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        event.value = Event.AddedToCard
                    }, {}).run(compositeDisposable::add)
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}