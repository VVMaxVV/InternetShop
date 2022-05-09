package com.example.internetshop.domain.data.usecase

import com.example.internetshop.domain.data.model.product.BagProduct
import io.reactivex.Completable

interface DeleteFromBagUseCase {
    fun execute(product: BagProduct): Completable
}