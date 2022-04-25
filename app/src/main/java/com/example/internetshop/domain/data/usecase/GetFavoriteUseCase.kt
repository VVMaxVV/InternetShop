package com.example.internetshop.domain.data.usecase

import com.example.internetshop.domain.data.model.product.Product
import io.reactivex.Completable
import io.reactivex.Single

interface GetFavoriteUseCase {
    fun execute(sortType: Int): Single<List<Product>>
    fun execute(): Single<List<Product>>
    fun getIdAllProduct(): Single<List<String>>
    fun updateProductInDB(product: Product): Completable
}