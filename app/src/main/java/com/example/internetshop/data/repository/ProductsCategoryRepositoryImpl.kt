package com.example.internetshop.data.repository

import com.example.internetshop.data.exception.NoSuchCategoryException
import com.example.internetshop.data.request.CategoryRequest
import com.example.internetshop.data.response.mapper.ProductServerMapper
import com.example.internetshop.data.retrofitapi.CategoryApi
import com.example.internetshop.domain.data.mapper.ProductMapper
import com.example.internetshop.domain.data.model.product.SimpleProduct
import com.example.internetshop.domain.data.repository.ProductsCategoryRepository
import io.reactivex.Single
import javax.inject.Inject

class ProductsCategoryRepositoryImpl @Inject constructor(
    private val categoryApi: CategoryApi,
    private val productMapper: ProductMapper,
    private val productServerMapper: ProductServerMapper
) : ProductsCategoryRepository {
    override fun getProductsCategory(categoryRequest: CategoryRequest): Single<List<SimpleProduct>> {
        return categoryApi.getCategoryProducts(
            categoryRequest.getCategoryName() ?: throw NoSuchCategoryException(
                "Category name cannot be null"
            )
        ).map {
            it.map {
                productMapper.toSimpleProduct(productServerMapper.toDomain(it))
            }
        }
    }
}