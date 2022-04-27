package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.usecase.GetAllIdFromDBUseCase
import com.example.internetshop.domain.data.usecase.GetProductFromServerUseCase
import com.example.internetshop.domain.data.usecase.GetResultUpdateFavoriteProductsUseCase
import com.example.internetshop.domain.data.usecase.GetUpdateProductsInDBUseCase
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetResultUpdateFavoriteProductUseCaseImpl @Inject constructor(
    private val allIdFromDBUseCase: GetAllIdFromDBUseCase,
    private val updateProductsInDBUseCase: GetUpdateProductsInDBUseCase,
    private val productFromServerUseCase: GetProductFromServerUseCase
) : GetResultUpdateFavoriteProductsUseCase {
    override fun execute(): Completable {
        return Completable
            .fromSingle(allIdFromDBUseCase.execute()
                .subscribeOn(Schedulers.io())
                .flattenAsObservable{ it }
                .flatMapSingle { productId ->
                    productFromServerUseCase.execute(productId)
                }.toList()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    updateProductsInDBUseCase.execute(it)
                }
            )
    }
}