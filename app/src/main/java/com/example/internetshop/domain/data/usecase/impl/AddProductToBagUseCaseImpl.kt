package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.product.BagProduct
import com.example.internetshop.domain.data.repository.BagRepository
import com.example.internetshop.domain.data.usecase.AddProductToBagUseCase
import io.reactivex.Completable
import javax.inject.Inject

class AddProductToBagUseCaseImpl @Inject constructor(
    private val repository: BagRepository
) : AddProductToBagUseCase {
    override fun execute(product: BagProduct?): Completable {
        return if (product != null) {
            repository.add(product)
        } else Completable.error(NullPointerException("Can't add null in DB"))
    }
}