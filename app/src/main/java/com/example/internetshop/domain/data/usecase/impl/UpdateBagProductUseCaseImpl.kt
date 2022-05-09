package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.product.BagProduct
import com.example.internetshop.domain.data.repository.BagRepository
import com.example.internetshop.domain.data.usecase.UpdateBagProductUseCase
import io.reactivex.Completable
import javax.inject.Inject

class UpdateBagProductUseCaseImpl @Inject constructor(
    private val cartRepository: BagRepository
) : UpdateBagProductUseCase {
    override fun execute(product: BagProduct?): Completable {
        return if (product != null) cartRepository.update(product)
        else Completable.error(NullPointerException())
    }

    override fun execute(productsList: List<BagProduct>): Completable {
        return cartRepository.update(productsList)
    }
}