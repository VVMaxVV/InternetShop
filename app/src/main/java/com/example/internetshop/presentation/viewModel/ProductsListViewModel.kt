package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.mapper.SimpleProductMapper
import com.example.internetshop.domain.data.model.product.SimpleProduct
import com.example.internetshop.domain.data.usecase.GetProductsFromCategoryUseCase
import com.example.internetshop.model.data.viewStates.ProductViewState
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductsListViewModel @Inject constructor(
    private val GetProductsUseCase: GetProductsFromCategoryUseCase,
    private val simpleProductMapper: SimpleProductMapper
) : BaseViewModel() {
    val productsList = MutableLiveData<List<ProductViewState>>()
    val navEventLiveData = SingleLiveEvent<Event>()
    val progressBarLiveData = MutableLiveData<Boolean>()

    var categoryName = ""

    fun getCategoryProductList() {
        compositeDisposable.add(
            GetProductsUseCase.execute(categoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { progressBarLiveData.value = true }
                .doFinally { progressBarLiveData.value = false }
                .subscribe({
                    productsList.value = it.map {
                        simpleProductMapper.toProductViewState(it).also {
                            compositeDisposable.add(it.events.subscribe() {
                                when (it) {
                                    is ProductViewState.Event.OnClick -> {
                                        Log.i(
                                            "Click event",
                                            "User clicked on a product: ${it.id}"
                                        )
                                        navEventLiveData.value =
                                            Event.OpenProductDetailEvent(it.id, it.productName)
                                    }
                                }
                            })
                        }
                    }
                },
                    {
                        Log.e("Error", "Error: ${it.message}")
                    })
        )
    }

    sealed class Event {
        data class OpenProductDetailEvent(val id: String, val productName: String) : Event()
        data class ToastEvent(val text: String) : Event()
    }

    fun map(simpleProduct: SimpleProduct): ProductViewState {
        simpleProduct.also {
            return simpleProductMapper.toProductViewState(it)
        }
    }
}