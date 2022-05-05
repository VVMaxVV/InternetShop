package com.example.internetshop.model.implementation

import com.example.internetshop.data.cache.BagProductDao
import com.example.internetshop.data.entity.mapper.BagEntityMapper
import com.example.internetshop.domain.data.model.product.BagProduct
import com.example.internetshop.domain.data.repository.BagRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class BagRepositoryImpl @Inject constructor(
    private val bagDao: BagProductDao,
    private val bagEntityMapper: BagEntityMapper
) : BagRepository {
    override fun getProduct(id: String): Single<BagProduct> {
        return Single.just(
            bagEntityMapper.toDomain(bagDao.get(id.toInt()))
        )
    }

    override fun getProducts(): Single<List<BagProduct>> {
        return Single.just(bagDao.get().map {
            bagEntityMapper.toDomain(it)
        })
    }

    override fun add(product: BagProduct): Completable {
        return bagDao.add(bagEntityMapper.toEntity(product))
    }

    override fun add(productsList: List<BagProduct>): Completable {
        return bagDao.add(productsList.map {
            bagEntityMapper.toEntity(it)
        })
    }

    override fun delete(product: BagProduct): Completable {
        return bagDao.delete(bagEntityMapper.toEntity(product))
    }

    override fun delete(productList: List<BagProduct>): Completable {
        return bagDao.delete(productList.map {
            bagEntityMapper.toEntity(it)
        })
    }

    override fun update(product: BagProduct): Completable {
        return bagDao.update(bagEntityMapper.toEntity(product))
    }

    override fun update(productList: List<BagProduct>): Completable {
        return bagDao.update(productList.map {
            bagEntityMapper.toEntity(it)
        })
    }
}