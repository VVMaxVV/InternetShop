package com.example.internetshop.model.implementation

import com.example.internetshop.data.cache.FavoriteProductsDao
import com.example.internetshop.data.entity.mapper.ProductEntityMapper
import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductRepositoryCash
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ProductRepositoryCashImpl @Inject constructor(
    private val favoriteDao: FavoriteProductsDao,
    private val productEntityMapper: ProductEntityMapper
) : ProductRepositoryCash {

    override fun addToFavorite(product: Product): Completable {
        return favoriteDao.insertToDB(
            productEntityMapper.toEntity(product)
        )
    }

    override fun getFavoriteProductList(): Single<List<Product>> {
        return Single.just(favoriteDao.getAllFromDB()
            .map {
                productEntityMapper.toDomain(it)
            })
    }
}