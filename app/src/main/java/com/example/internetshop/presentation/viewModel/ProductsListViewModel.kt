package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.data.request.CategoryRequest
import com.example.internetshop.domain.data.mapper.SimpleProductMapper
import com.example.internetshop.domain.data.model.product.SimpleProduct
import com.example.internetshop.domain.data.usecase.GetProductsFromCategoryUseCase
import com.example.internetshop.model.data.viewStates.ProductViewState
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductsListViewModel @Inject constructor(
    private val fromCategoryUseCase: GetProductsFromCategoryUseCase,
    private val simpleProductMapper: SimpleProductMapper
) : BaseViewModel() {
    val productsList = MutableLiveData<List<ProductViewState>>()
    val navEventLiveData = SingleLiveEvent<Event>()

    fun getCategoryProductList(categoryName: String) {
        compositeDisposable.add(
            fromCategoryUseCase.execute(CategoryRequest(categoryName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                                            Event.OpenProductDetailEvent(it.id)
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
        data class OpenProductDetailEvent(val id: String) : Event()
        data class ToastEvent(val text: String) : Event()
    }

    fun map(simpleProduct: SimpleProduct): ProductViewState {
        simpleProduct.also {
            return simpleProductMapper.toProductViewState(it)
        }
    }
}