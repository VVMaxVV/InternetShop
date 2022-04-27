package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.repository.ProductRepositoryCash
import com.example.internetshop.domain.data.usecase.GetAllIdFromDBUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetAllIdFromDBUseCaseImpl @Inject constructor(val productRepository: ProductRepositoryCash) :
    GetAllIdFromDBUseCase {
    override fun execute(): Single<List<String>> {
        return productRepository.getIdAllProduct()
    }
}