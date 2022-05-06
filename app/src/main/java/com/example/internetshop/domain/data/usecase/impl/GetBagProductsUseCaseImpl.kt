package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.product.BagProduct
import com.example.internetshop.domain.data.repository.BagRepository
import com.example.internetshop.domain.data.usecase.GetBagProductsUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetBagProductsUseCaseImpl @Inject constructor(
    private val cartRepository: BagRepository
) : GetBagProductsUseCase {

    override fun execute(): Single<List<BagProduct>> {
        return cartRepository.getProducts()
    }
}