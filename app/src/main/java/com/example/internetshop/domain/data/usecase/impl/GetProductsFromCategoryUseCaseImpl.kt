package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.data.request.CategoryRequest
import com.example.internetshop.domain.data.model.product.SimpleProduct
import com.example.internetshop.domain.data.repository.ProductsCategoryRepository
import com.example.internetshop.domain.data.usecase.GetProductsFromCategoryUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetProductsFromCategoryUseCaseImpl @Inject constructor(
    private val categoryRepository: ProductsCategoryRepository
) : GetProductsFromCategoryUseCase {
    override fun execute(categoryRequest: CategoryRequest): Single<List<SimpleProduct>> {
        return categoryRepository.getProductsCategory(categoryRequest)
    }
}