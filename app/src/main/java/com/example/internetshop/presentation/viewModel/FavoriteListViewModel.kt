package com.example.internetshop.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.mapper.ProductMapper
import com.example.internetshop.domain.data.model.product.SimpleProduct
import com.example.internetshop.domain.data.usecase.GetFavoriteUseCase
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoriteListViewModel @Inject constructor(private val getFavoriteUseCase: GetFavoriteUseCase) : BaseViewModel() {
    val event = SingleLiveEvent<Event>()
    val productsLiveData = MutableLiveData<List<SimpleProduct>>()

    fun onProductClicked(product: SimpleProduct) {
        event.value = Event.OpenProductDetailEvent(product.id,product.title)
    }

    fun getProductsList() {
        compositeDisposable.add(getFavoriteUseCase.execute()
            .subscribeOn(Schedulers.io())
            .map { it ->
                it.map {
                    ProductMapper().toSimpleProduct(it)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                productsLiveData.value = it
            }, {
                event.value = Event.ToastEvent("$it.message")
            }))
    }
    sealed class Event {
        data class OpenProductDetailEvent(val id: String, val productName: String) : Event()
        data class ToastEvent(val text: String) : Event()
    }
}