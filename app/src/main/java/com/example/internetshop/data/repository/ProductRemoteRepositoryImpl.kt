package com.example.internetshop.data.repository

import com.example.internetshop.data.response.mapper.ProductServerMapper
import com.example.internetshop.data.retrofitapi.ProductApi
import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductRemoteRepository
import io.reactivex.Single
import javax.inject.Inject

class ProductRemoteRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val productMapper: ProductServerMapper
) :
    ProductRemoteRepository {
    override fun getProduct(id: String): Single<Product> {
        return productApi.getProductRx(id).map {
            productMapper.toDomain(it)
        }
    }

    override fun getProducts(): Single<List<Product>> {
        return productApi.getProductsRx().map {
            it.map {
                productMapper.toDomain(it)
            }
        }
    }
}