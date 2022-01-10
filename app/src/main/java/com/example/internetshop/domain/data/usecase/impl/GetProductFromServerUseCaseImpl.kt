package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductRepository
import com.example.internetshop.domain.data.usecase.GetProductFromServerUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetProductFromServerUseCaseImpl @Inject constructor(val productRepository: ProductRepository) :GetProductFromServerUseCase {
    override fun execute(id: String): Single<Product> {
        return productRepository.getProductRx(id)
    }
}