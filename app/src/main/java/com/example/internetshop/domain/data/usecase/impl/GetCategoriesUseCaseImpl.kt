package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.mapper.CategoryMapper
import com.example.internetshop.domain.data.model.category.BaseCategory
import com.example.internetshop.domain.data.repository.CategoryRepository
import com.example.internetshop.domain.data.usecase.GetCategoriesUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetCategoriesUseCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val categoryMapper: CategoryMapper
) :
    GetCategoriesUseCase {
    override fun execute(): Single<List<BaseCategory>> {
        return categoryRepository.getCategoryList().map {
            categoryMapper.toBaseCategory(it)
        }
    }
}
