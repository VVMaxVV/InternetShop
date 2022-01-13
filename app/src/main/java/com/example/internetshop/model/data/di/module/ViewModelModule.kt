package com.example.internetshop.model.data.di.module

import androidx.lifecycle.ViewModel
import com.example.internetshop.presentation.viewModel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @[IntoMap ViewModelKey(ProductDetailsViewModel::class)]
    fun provideMainActivityViewModel(mainViewModel: ProductDetailsViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(AuthenticationViewModel::class)]
    fun provideAuthViewModel(authViewModule: AuthenticationViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(ProductsActivityViewModel::class)]
    fun provideProductsActivityViewModule(authViewModule: ProductsActivityViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(ReviewViewModel::class)]
    fun provideReviewViewModel(reviewViewModel: ReviewViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(FavoriteListViewModel::class)]
    fun provideFavoriteListViewModel(favoriteListViewModel: FavoriteListViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(ProductDetailServerViewModel::class)]
    fun provideProductDetaolServerViewModel(productDetailServerViewModel: ProductDetailServerViewModel): ViewModel
}