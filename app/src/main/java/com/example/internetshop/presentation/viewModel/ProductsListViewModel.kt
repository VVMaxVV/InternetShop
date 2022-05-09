package com.example.internetshop.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.mapper.SimpleProductMapper
import com.example.internetshop.domain.data.model.product.SimpleProduct
import com.example.internetshop.domain.data.usecase.*
import com.example.internetshop.model.data.viewStates.ProductViewState
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductsListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsFromCategoryUseCase,
    private val simpleProductMapper: SimpleProductMapper,
    private val productInDBUseCase: ProductInDBUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val getProductFromServerUseCase: GetProductFromServerUseCase
) : BaseViewModel() {
    val productsList = MutableLiveData<List<ProductViewState>>()
    val events = SingleLiveEvent<Event>()
    val progressBar = MutableLiveData<Boolean>()

    var categoryName = ""

    fun getCategoryProductList() {
        compositeDisposable.add(
            getProductsUseCase.execute(categoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { progressBar.value = true }
                .doFinally { progressBar.value = false }
                .subscribe({
                    productsList.value = it.map { simpleProduct ->
                        simpleProductMapper.toProductViewState(simpleProduct)
                            .also { productViewState ->
                                productInDBUseCase.execute(simpleProduct.id)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({
                                        productViewState.favorite.value = it
                                    }, {
                                        events.value = Event.ReceiveThrowable(it)
                                    }).run(compositeDisposable::add)
                                compositeDisposable.add(productViewState.events.subscribe { productViewStateEvent ->
                                    when (productViewStateEvent) {
                                        is ProductViewState.Event.OnClick -> {
                                            onClick(productViewStateEvent)
                                        }
                                        is ProductViewState.Event.FavoriteToggleClick -> {
                                            when (productViewStateEvent.product.favorite.value) {
                                                false -> getProductFromServerUseCase.execute(
                                                    productViewStateEvent.product.id
                                                )
                                                    .subscribeOn(Schedulers.io())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribe({
                                                        addToFavoriteUseCase.execute(it)
                                                            .subscribe()
                                                    }, {
                                                        events.value = Event.ReceiveThrowable(it)
                                                        productViewStateEvent.product.favorite.value =
                                                            false
                                                    }).run(compositeDisposable::add)
                                                true -> getProductFromServerUseCase.execute(
                                                    productViewStateEvent.product.id
                                                )
                                                    .subscribeOn(Schedulers.io())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribe({
                                                        deleteFromFavoriteUseCase.execute(it)
                                                            .subscribe()
                                                    }, {
                                                        events.value = Event.ReceiveThrowable(it)
                                                        productViewStateEvent.product.favorite.value =
                                                            true
                                                    }).run(compositeDisposable::add)
                                            }
                                        }
                                    }
                                })
                            }
                    }
                },
                    {
                        events.value = Event.ReceiveThrowable(it)
                    })
        )
    }

    private fun onClick(productViewStateEvent: ProductViewState.Event.OnClick) {
        events.value =
            Event.OpenProductDetailEvent(
                productViewStateEvent.id,
                productViewStateEvent.productName
            )
    }

    sealed class Event {
        data class OpenProductDetailEvent(val id: String, val productName: String) : Event()
        data class ToastEvent(val text: String) : Event()
        data class ReceiveThrowable(val throwable: Throwable) : Event()
    }

    fun map(simpleProduct: SimpleProduct): ProductViewState {
        simpleProduct.also {
            return simpleProductMapper.toProductViewState(it)
        }
    }
}