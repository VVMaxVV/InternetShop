package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductRepositoryCash
import com.example.internetshop.domain.data.usecase.GetUpdateProductsInDBUseCase
import io.reactivex.Completable
import javax.inject.Inject

class GetUpdateProductsInDBUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepositoryCash
) : GetUpdateProductsInDBUseCase {
    override fun execute(productList: List<Product>): Completable {
        return productRepository.updateProductsDate(productList)
    }
}