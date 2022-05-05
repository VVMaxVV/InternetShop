package com.example.internetshop.domain.data.mapper

import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.model.product.BagProduct
import com.example.internetshop.model.data.viewStates.BagProductViewState
import java.util.*
import javax.inject.Inject

class BagProductMapper @Inject constructor() {
    fun toBagProduct(product: BagProductViewState): BagProduct? {
        return product.amount.value?.let {
            BagProduct(
                product.id,
                product.imageURL,
                product.price.toFloat(),
                product.title,
                product.color,
                product.size,
                it
            )
        }
    }

    fun toBagProductViewState(product: BagProduct): BagProductViewState {
        return BagProductViewState(
            product.id,
            product.imageURL,
            String.format(Locale.ROOT, "%.2f", product.price),
            product.title,
            product.color,
            product.size,
            MutableLiveData(product.amount)
        )
    }
}