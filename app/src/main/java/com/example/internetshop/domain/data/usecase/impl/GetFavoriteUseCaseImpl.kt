package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.data.exception.UnknownFavoriteProductsQueryParameter
import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductRepositoryCash
import com.example.internetshop.domain.data.usecase.GetFavoriteUseCase
import com.example.internetshop.presentation.viewModel.FavoriteListViewModel
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class GetFavoriteUseCaseImpl @Inject constructor(val productRepository: ProductRepositoryCash) :
    GetFavoriteUseCase {
    override fun execute(sortType: Int): Single<List<Product>> {
        return when (sortType) {
            FavoriteListViewModel.ALL_PRODUCTS ->
                productRepository.getFavoriteProductListDescending()
            FavoriteListViewModel.SORT_BY_NAME ->
                productRepository.getFavoriteProductByName()
            FavoriteListViewModel.SORT_BY_NAME_DESCENDING ->
                productRepository.getFavoriteProductByNameDescending()
            FavoriteListViewModel.SORT_BY_PRICE ->
                productRepository.getFavoriteProductByPrice()
            FavoriteListViewModel.SORT_BY_PRICE_DESCENDING ->
                productRepository.getFavoriteProductByPriceDescending()
            FavoriteListViewModel.SORT_BY_RATING ->
                productRepository.getFavoriteProductByRating()
            FavoriteListViewModel.SORT_BY_RATING_DESCENDING ->
                productRepository.getFavoriteProductByRatingDescending()
            else ->
                Single.error(UnknownFavoriteProductsQueryParameter("Unknown parameter: $sortType"))
        }
    }

    override fun execute(): Single<List<Product>> {
        return productRepository.getFavoriteProductList()
    }

    override fun getIdAllProduct(): Single<List<String>> {
        return productRepository.getIdAllProduct()
    }

    override fun updateProductInDB(product: Product): Completable {
        return productRepository.updateProductDate(product)
    }
}