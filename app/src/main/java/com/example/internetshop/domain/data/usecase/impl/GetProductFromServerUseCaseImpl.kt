package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductRemoteRepository
import com.example.internetshop.domain.data.usecase.GetProductFromServerUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetProductFromServerUseCaseImpl @Inject constructor(val productRemoteRepository: ProductRemoteRepository) :GetProductFromServerUseCase {
    override fun execute(id: String): Single<Product> {
        return productRemoteRepository.getProduct(id)
    }
}