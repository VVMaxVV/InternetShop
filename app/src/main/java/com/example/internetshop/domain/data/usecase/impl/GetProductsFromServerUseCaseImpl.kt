package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductRemoteRepository
import com.example.internetshop.domain.data.usecase.GetProductsFromServerUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetProductsFromServerUseCaseImpl @Inject constructor(
    val productRemoteRepository: ProductRemoteRepository
) : GetProductsFromServerUseCase {
    override fun execute(): Single<List<Product>> {
        return productRemoteRepository.getProducts()
    }
}