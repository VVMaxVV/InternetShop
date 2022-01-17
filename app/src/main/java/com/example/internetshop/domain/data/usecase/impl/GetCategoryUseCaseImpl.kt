package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.Category
import com.example.internetshop.domain.data.repository.CategoryRepository
import com.example.internetshop.domain.data.usecase.GetCategoryUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetCategoryUseCaseImpl @Inject constructor(private val categoryRepository: CategoryRepository) :
    GetCategoryUseCase {
    override fun execute(): Single<List<Category>> {
        return categoryRepository.getCategoryList()
    }
}