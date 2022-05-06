package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.data.exception.UnknownFavoriteProductsQueryParameter
import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductLocalRepository
import com.example.internetshop.domain.data.usecase.GetFavoriteUseCase
import com.example.internetshop.presentation.viewModel.FavoriteListViewModel
import io.reactivex.Single
import javax.inject.Inject

class GetFavoriteUseCaseImpl @Inject constructor(val productLocalRepository: ProductLocalRepository) :
    GetFavoriteUseCase {
    override fun execute(sortType: Int): Single<List<Product>> {
        return when (sortType) {
            FavoriteListViewModel.ALL_PRODUCTS ->
                productLocalRepository.getFavoriteProductListDescending()
            FavoriteListViewModel.SORT_BY_NAME ->
                productLocalRepository.getFavoriteProductByName()
            FavoriteListViewModel.SORT_BY_NAME_DESCENDING ->
                productLocalRepository.getFavoriteProductByNameDescending()
            FavoriteListViewModel.SORT_BY_PRICE ->
                productLocalRepository.getFavoriteProductByPrice()
            FavoriteListViewModel.SORT_BY_PRICE_DESCENDING ->
                productLocalRepository.getFavoriteProductByPriceDescending()
            FavoriteListViewModel.SORT_BY_RATING ->
                productLocalRepository.getFavoriteProductByRating()
            FavoriteListViewModel.SORT_BY_RATING_DESCENDING ->
                productLocalRepository.getFavoriteProductByRatingDescending()
            else ->
                Single.error(UnknownFavoriteProductsQueryParameter("Unknown parameter: $sortType"))
        }
    }
}