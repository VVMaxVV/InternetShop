package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.repository.ProductLocalRepository
import com.example.internetshop.domain.data.usecase.GetAllFavoriteIdsUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetAllFavoriteIdsUseCaseImpl @Inject constructor(val productLocalRepository: ProductLocalRepository) :
    GetAllFavoriteIdsUseCase {
    override fun execute(): Single<List<String>> {
        return productLocalRepository.getIdAllProduct()
    }
}