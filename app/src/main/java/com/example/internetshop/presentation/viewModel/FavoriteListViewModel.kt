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
    val openDetailsEvent = SingleLiveEvent<String>()
    val productsLiveData = MutableLiveData<List<SimpleProduct>>()

    fun onProductClicked(product: SimpleProduct) {
        openDetailsEvent.value = product.id
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
                openDetailsEvent.value = "${it.message}"
            }))
    }
}