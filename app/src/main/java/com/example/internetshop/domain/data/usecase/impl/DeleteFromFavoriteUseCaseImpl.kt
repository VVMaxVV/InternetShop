package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductLocalRepository
import com.example.internetshop.domain.data.usecase.DeleteFromFavoriteUseCase
import io.reactivex.Completable
import javax.inject.Inject

class DeleteFromFavoriteUseCaseImpl @Inject constructor(
    private val productLocalRepository: ProductLocalRepository
) : DeleteFromFavoriteUseCase {
    override fun execute(product: Product): Completable {
        return productLocalRepository.deleteFromFavorite(product)
    }
}