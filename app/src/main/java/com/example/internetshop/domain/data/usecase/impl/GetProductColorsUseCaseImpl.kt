package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.usecase.GetProductColorsUseCase
import com.example.internetshop.model.data.factory.ColorFactory
import io.reactivex.Single
import javax.inject.Inject

class GetProductColorsUseCaseImpl @Inject constructor(
    private val factory: ColorFactory
) : GetProductColorsUseCase {
    override fun execute(): Single<List<String>> {
        return Single.just(factory.get((0..4).random()))
    }
}