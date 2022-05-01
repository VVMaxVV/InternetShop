package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductLocalRepository
import com.example.internetshop.domain.data.usecase.UpdateLocalFavoritesUseCase
import io.reactivex.Completable
import javax.inject.Inject

class UpdateLocalFavoritesUseCaseImpl @Inject constructor(
    private val productLocalRepository: ProductLocalRepository
) : UpdateLocalFavoritesUseCase {
    override fun execute(productList: List<Product>): Completable {
        return productLocalRepository.updateProductsDate(productList)
    }
}