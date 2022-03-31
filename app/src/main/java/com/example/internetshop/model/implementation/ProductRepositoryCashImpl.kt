package com.example.internetshop.model.implementation

import com.example.internetshop.data.cache.FavoriteProductsDao
import com.example.internetshop.data.entity.FavoriteProductEntity
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

    override fun deleteFromFavorite(product: Product): Completable {
        return favoriteDao.deleteFromDB(
            productEntityMapper.toEntity(product)
        )
    }

    override fun getFavoriteProductList(): Single<List<Product>> {
        return Single.just(favoriteDao.getAllFromDB()
            .map {
                productEntityMapper.toDomain(it)
            })
    }

    override fun getFavoriteProductById(id: String): Single<Product> {
        return Single.just(
            productEntityMapper
                .toDomain(
                    favoriteDao.getProductByIdFromDB(id.toInt()) ?: FavoriteProductEntity(
                        id,
                        "https://www.answersreviews.com/wp-content/uploads/2020/06/Fix-Error404-on-WordPress-780x400.jpg",
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A",
                        0f,
                        0
                    )
                )
        )
    }

    override fun isProductInDB(product: Product): Single<Boolean> {
        return if(favoriteDao.getProductByIdFromDB(product.id.toInt())!=null) Single.just(true)
        else Single.just(false)
    }
}