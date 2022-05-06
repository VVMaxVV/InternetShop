package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.mapper.BagProductMapper
import com.example.internetshop.domain.data.usecase.DeleteFromBagUseCase
import com.example.internetshop.domain.data.usecase.GetBagProductsUseCase
import com.example.internetshop.domain.data.usecase.UpdateBagProductUseCase
import com.example.internetshop.model.data.viewStates.BagProductViewState
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class BagViewModel @Inject constructor(
    private val bagProductMapper: BagProductMapper,
    private val getBagProductsUseCase: GetBagProductsUseCase,
    private val updateBagUseCase: UpdateBagProductUseCase,
    private val deleteFromBagUseCase: DeleteFromBagUseCase
) : BaseViewModel() {
    val totalPrice = MutableLiveData("0")
    val productList = MutableLiveData<MutableList<BagProductViewState>>()
    val events = SingleLiveEvent<Event>()

    fun getProducts() {
        getBagProductsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repositoryProductList ->
                productList.value = repositoryProductList.mapIndexed { position, bagProduct ->
                    Log.i(
                        BagViewModel::class.java.name,
                        "Product: \nid:${bagProduct.id} \ntitle:${bagProduct.title}"
                    )
                    bagProductMapper.toBagProductViewState(bagProduct).also { bagProductViewState ->
                        bagProductViewState.events
                            .subscribe { it
                                when (it) {
                                    is BagProductViewState.Event.OnClick -> {
                                        onClick(it)
                                    }

                                    is BagProductViewState.Event.OnMinusClick -> {
                                        onMinus(bagProductViewState, position)
                                    }

                                    is BagProductViewState.Event.OnPlusClick -> {
                                        onPlus(bagProductViewState, position)
                                    }
                                }
                                calculateTotalPrice()
                            }.run(compositeDisposable::add)
                    }
                }.toMutableList()
                calculateTotalPrice()
            }, {
                Log.e(BagViewModel::class.java.name, "Error: ${it.message}")
            }).run(compositeDisposable::add)
    }

    private fun onClick(bagProductViewStateEvent: BagProductViewState.Event.OnClick) {
        events.value =
            Event.OpenProduct(
                bagProductViewStateEvent.id.toString(),
                bagProductViewStateEvent.productName
            )
        Log.i(
            BagViewModel::class.java.name,
            "Open product (id: ${bagProductViewStateEvent.id})"
        )
    }

    private fun onPlus(bagProductViewState: BagProductViewState, position: Int) {
        updateBagUseCase.execute(
            bagProductMapper.toBagProduct(
                bagProductViewState.apply {
                    amount.value =
                        amount.value?.plus(1)
                }
            )
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i(
                    BagViewModel::class.java.name,
                    "Plus product $position. Value:${bagProductViewState.amount.value})"
                )
            }, {
                bagProductViewState.amount.value =
                    bagProductViewState.amount.value?.minus(1)
                Log.e(
                    BagViewModel::class.java.name,
                    "Error update product on DB: ${it.message}"
                )
            })
            .run(compositeDisposable::add)
    }

    private fun onMinus(bagProductViewState: BagProductViewState, position: Int) {
        if (bagProductViewState.amount.value == 1) {
            productList.value?.removeAt(position)
            bagProductMapper.toBagProduct(
                bagProductViewState
            )?.let { product ->
                deleteFromBagUseCase.execute(
                    product
                ).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({}, {
                        Log.e(
                            "BagViewModel",
                            "Error: ${it.message}"
                        )
                    })
            }
            events.value = Event.DeleteFromBag(position)
        }
        if (bagProductViewState.amount.value ?: 0 > 1) {
            updateBagUseCase.execute(
                bagProductMapper.toBagProduct(
                    bagProductViewState.apply {
                        amount.value =
                            amount.value?.minus(1)
                    }
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i(
                        BagViewModel::class.java.name,
                        "Minus product $position. Value:${bagProductViewState.amount.value})"
                    )
                }, {
                    bagProductViewState.amount.value =
                        bagProductViewState.amount.value?.plus(1)
                    Log.e(
                        BagViewModel::class.java.name,
                        "Error update product on DB: ${it.message}"
                    )
                })
                .run(compositeDisposable::add)
        }
    }

    private fun calculateTotalPrice() {
        var price = 0f
        productList.value?.forEach { product ->
            product.amount.value?.let {
                price += product.price.toFloat() * it
            }
        }
        totalPrice.value = String.format(Locale.ROOT, "%.2f", price)
    }

    sealed class Event {
        data class OpenProduct(val id: String, val productName: String) : Event()
        data class DeleteFromBag(val position: Int) : Event()
    }
}