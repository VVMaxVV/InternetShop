package com.example.internetshop.domain.data.usecase

import com.example.internetshop.domain.data.model.product.BagProduct
import io.reactivex.Completable

interface UpdateBagProductUseCase {
    fun execute(product: BagProduct?): Completable
    fun execute(productsList: List<BagProduct>): Completable
}