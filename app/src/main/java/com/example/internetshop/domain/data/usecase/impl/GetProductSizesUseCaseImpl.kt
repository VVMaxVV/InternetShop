package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.usecase.GetProductSizesUseCase
import com.example.internetshop.model.data.factory.SizesFactory
import io.reactivex.Single
import javax.inject.Inject

class GetProductSizesUseCaseImpl @Inject constructor(
    private val factory: SizesFactory
) : GetProductSizesUseCase {
    override fun execute(): Single<List<String>> {
        return Single.just(factory.get((0..4).random()))
    }
}