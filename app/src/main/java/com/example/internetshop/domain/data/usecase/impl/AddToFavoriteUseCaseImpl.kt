package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductLocalRepository
import com.example.internetshop.domain.data.usecase.AddToFavoriteUseCase
import io.reactivex.Completable
import javax.inject.Inject

class AddToFavoriteUseCaseImpl @Inject constructor(
    private val productLocalRepository: ProductLocalRepository
) : AddToFavoriteUseCase {
    override fun execute(product: Product): Completable {
        return productLocalRepository.addToFavorite(product)
    }
}