package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductRepository
import com.example.internetshop.domain.data.usecase.GetProductsFromServerUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetProductsFromServerUseCaseImpl @Inject constructor(val productRepository: ProductRepository) :GetProductsFromServerUseCase {
    override fun execute(): Single<List<Product>> {
        return productRepository.getProductsRx()
    }
}