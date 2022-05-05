package com.example.internetshop.domain.data.usecase

import com.example.internetshop.domain.data.model.product.BagProduct
import io.reactivex.Single

interface GetBagProductsUseCase {
    fun execute(id: String): Single<BagProduct>
    fun execute(): Single<List<BagProduct>>
}