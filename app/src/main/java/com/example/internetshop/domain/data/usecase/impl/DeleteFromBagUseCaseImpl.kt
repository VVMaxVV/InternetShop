package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.product.BagProduct
import com.example.internetshop.domain.data.repository.BagRepository
import com.example.internetshop.domain.data.usecase.DeleteFromBagUseCase
import io.reactivex.Completable
import javax.inject.Inject

class DeleteFromBagUseCaseImpl @Inject constructor(
    private val repository: BagRepository
) : DeleteFromBagUseCase {
    override fun execute(product: BagProduct): Completable {
        return repository.delete(product)
    }
}