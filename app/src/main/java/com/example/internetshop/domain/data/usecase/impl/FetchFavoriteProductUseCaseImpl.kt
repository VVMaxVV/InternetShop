package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.usecase.FetchFavoriteProductsUseCase
import com.example.internetshop.domain.data.usecase.GetAllFavoriteIdsUseCase
import com.example.internetshop.domain.data.usecase.GetProductFromServerUseCase
import com.example.internetshop.domain.data.usecase.UpdateLocalFavoritesUseCase
import io.reactivex.Completable
import javax.inject.Inject

class FetchFavoriteProductUseCaseImpl @Inject constructor(
    private val allFavoriteIdsUseCase: GetAllFavoriteIdsUseCase,
    private val updateLocalFavoritesUseCase: UpdateLocalFavoritesUseCase,
    private val productFromServerUseCase: GetProductFromServerUseCase
) : FetchFavoriteProductsUseCase {
    override fun execute(): Completable {
        return Completable
            .fromSingle(allFavoriteIdsUseCase.execute()
                .flattenAsObservable{ it }
                .flatMapSingle { productId ->
                    productFromServerUseCase.execute(productId)
                }.toList()
                .doOnSuccess {
                    updateLocalFavoritesUseCase.execute(it)
                }
            )
    }
}