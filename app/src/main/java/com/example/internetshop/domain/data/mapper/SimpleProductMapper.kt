package com.example.internetshop.domain.data.mapper

import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.model.product.SimpleProduct
import com.example.internetshop.model.data.viewStates.ProductViewState
import javax.inject.Inject

class SimpleProductMapper @Inject constructor() {
    fun toProductViewState(simpleProduct: SimpleProduct): ProductViewState {
        return ProductViewState(
            simpleProduct.imageUrl,
            simpleProduct.title,
            simpleProduct.price,
            simpleProduct.rating,
            "(${simpleProduct.numberOfReviews})",
            simpleProduct.id,
            MutableLiveData()
        )
    }
}