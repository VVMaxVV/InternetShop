package com.example.internetshop.data.repository

import com.example.internetshop.data.response.mapper.ProductServerMapper
import com.example.internetshop.data.retrofitapi.ProductApi
import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductRepository
import io.reactivex.Single
import javax.inject.Inject

class ProductRepositoryServerImpl @Inject constructor(
    private val productApi: ProductApi,
    private val productMapper: ProductServerMapper
) :
    ProductRepository {
    override fun getProductRx(id: String): Single<Product> {
        return productApi.getProductRx(id).map {
            productMapper.toDomain(it)
        }
    }

    override fun getProductsRx(): Single<List<Product>> {
        return productApi.getProductsRx().map {
            it.map {
                productMapper.toDomain(it)
            }
        }
    }
}