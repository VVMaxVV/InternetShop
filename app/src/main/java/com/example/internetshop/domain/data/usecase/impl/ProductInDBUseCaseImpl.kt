package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.repository.ProductLocalRepository
import com.example.internetshop.domain.data.usecase.ProductInDBUseCase
import io.reactivex.Single
import javax.inject.Inject

class ProductInDBUseCaseImpl @Inject constructor(
    private val productLocalRepository: ProductLocalRepository
) : ProductInDBUseCase {
    override fun execute(id: String): Single<Boolean> {
        return productLocalRepository.isProductInDB(id)
    }
}