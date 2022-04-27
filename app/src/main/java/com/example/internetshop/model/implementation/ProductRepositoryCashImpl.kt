package com.example.internetshop.model.implementation

import com.example.internetshop.data.cache.FavoriteProductsDao
import com.example.internetshop.data.entity.mapper.ProductEntityMapper
import com.example.internetshop.data.exception.ProductNotFoundInDBException
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

    override fun updateProductDate(product: Product): Completable {
        return favoriteDao.updateProductDate(
            productEntityMapper.toEntity(product)
        )
    }

    override fun getFavoriteProductList(): Single<List<Product>> {
        return Single.just(favoriteDao.getAllFromDB()
            .map {
                productEntityMapper.toDomain(it)
            })
    }

    override fun getFavoriteProductListDescending(): Single<List<Product>> {
        return Single.just(favoriteDao.getAllFromDB()
            .map {
                productEntityMapper.toDomain(it)
            }.reversed()
        )
    }

    override fun getFavoriteProductById(id: String): Single<Product> {
        return when (val product = favoriteDao.getProductByIdFromDB(id.toInt())) {
            null -> Single.error(
                ProductNotFoundInDBException(
                    "Product with ID:$id not found"
                )
            )
            else -> {
                Single.just(productEntityMapper.toDomain(product))
            }
        }
    }

    override fun isProductInDB(product: Product): Single<Boolean> {
        return if (favoriteDao.getProductByIdFromDB(product.id.toInt()) != null)
            Single.just(true)
        else Single.just(false)
    }

    override fun getFavoriteProductByName(): Single<List<Product>> {
        return Single.just(favoriteDao.getProductsByNameFromDB()
            .map {
                productEntityMapper.toDomain(it)
            })
    }

    override fun getFavoriteProductByNameDescending(): Single<List<Product>> {
        return Single.just(favoriteDao.getProductsByNameFromDBDescending()
            .map {
                productEntityMapper.toDomain(it)
            })

    }

    override fun getFavoriteProductByRating(): Single<List<Product>> {
        return Single.just(favoriteDao.getProductByRatingFromDB()
            .map {
                productEntityMapper.toDomain(it)
            })
    }

    override fun getFavoriteProductByRatingDescending(): Single<List<Product>> {
        return Single.just(favoriteDao.getProductByRatingFromDBDescending()
            .map {
                productEntityMapper.toDomain(it)
            })
    }

    override fun getFavoriteProductByPrice(): Single<List<Product>> {
        return Single.just(
            favoriteDao.getProductByPriceFromDB()
                .map {
                    productEntityMapper.toDomain(it)
                }
        )
    }

    override fun getFavoriteProductByPriceDescending(): Single<List<Product>> {
        return Single.just(
            favoriteDao.getProductByPriceFromDBDescending()
                .map {
                    productEntityMapper.toDomain(it)
                }
        )
    }

    override fun getIdAllProduct(): Single<List<String>> {
        return Single.just(
            favoriteDao.getAllId()
        )
    }

    override fun updateProductsDate(productList: List<Product>): Completable {
        return favoriteDao.updateProductsDate(productList.map {
            productEntityMapper.toEntity(it)
        })
    }
}