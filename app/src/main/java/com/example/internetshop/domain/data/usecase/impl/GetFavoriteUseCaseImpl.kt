package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductRepositoryCash
import com.example.internetshop.domain.data.usecase.GetFavoriteUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetFavoriteUseCaseImpl @Inject constructor(val productRepository: ProductRepositoryCash):
    GetFavoriteUseCase {
    override fun execute(): Single<List<Product>> {
        return productRepository.getFavoriteProductList()
    }
}