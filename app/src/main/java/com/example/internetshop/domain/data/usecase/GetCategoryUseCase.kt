package com.example.internetshop.domain.data.usecase

import com.example.internetshop.domain.data.model.Category
import io.reactivex.Single

interface GetCategoryUseCase {
    fun execute(): Single<List<Category>>
}