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
    private val bagProductsUseCase: GetBagProductsUseCase,
    private val updateBagUseCase: UpdateBagProductUseCase,
    private val deleteFromBagUseCase: DeleteFromBagUseCase
) : BaseViewModel() {
    val totalPrice = MutableLiveData("0")
    val productList = MutableLiveData<List<BagProductViewState>>()
    val events = SingleLiveEvent<Event>()

    fun getProducts() {
        bagProductsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repositoryProductList ->
                productList.value = repositoryProductList.map {
                    Log.i(
                        BagViewModel::class.java.name,
                        "Product: \nid:${it.id} \ntitle:${it.title}"
                    )
                    bagProductMapper.toBagProductViewState(it).also { bagProductViewState ->
                        bagProductViewState.events.subscribe {
                            when (it) {
                                is BagProductViewState.Event.OnClick -> {
                                    events.value =
                                        Event.OpenProduct(it.id.toString(), it.productName)
                                    Log.i(
                                        BagViewModel::class.java.name,
                                        "Open product (id: ${it.id})"
                                    )
                                }

                                is BagProductViewState.Event.OnMinusClick -> {
                                    if (bagProductViewState.amount.value == 1) {
                                        productList.value?.apply {
                                            toMutableList().remove(bagProductViewState)
                                            toList()
                                        }
                                        bagProductMapper.toBagProduct(
                                            bagProductViewState
                                        )?.let { product ->
                                            deleteFromBagUseCase.execute(
                                                product
                                            ).subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe({}, {
                                                    Log.e("BagViewModel", "Error: ${it.message}")
                                                })
                                        }
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
                                                calculateTotalPrice()
                                                Log.i(
                                                    BagViewModel::class.java.name,
                                                    "Minus product (id: ${it.id}. Value:${bagProductViewState.amount.value})"
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

                                is BagProductViewState.Event.OnPlusClick -> {
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
                                            calculateTotalPrice()
                                            Log.i(
                                                BagViewModel::class.java.name,
                                                "Plus product (id: ${it.id}. Value:${bagProductViewState.amount.value})"
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
                            }
                        }.run(compositeDisposable::add)
                    }
                }
                calculateTotalPrice()
            }, {
                Log.e(BagViewModel::class.java.name, "Error: ${it.message}")
            }).run(compositeDisposable::add)
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
        data class DeleteFromBag(val id: String) : Event()
    }
}