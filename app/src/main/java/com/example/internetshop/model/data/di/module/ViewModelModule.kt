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
    @[IntoMap ViewModelKey(ProductsListViewModel::class)]
    fun provideProductListViewModel(productsListViewModel: ProductsListViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(AuthenticationViewModel::class)]
    fun provideAuthViewModel(authViewModule: AuthenticationViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(ReviewViewModel::class)]
    fun provideReviewViewModel(reviewViewModel: ReviewViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(FavoriteListViewModel::class)]
    fun provideFavoriteListViewModel(favoriteListViewModel: FavoriteListViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(ProductDetailServerViewModel::class)]
    fun provideProductDetailServerViewModel(productDetailServerViewModel: ProductDetailServerViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(CategoriesViewModel::class)]
    fun provideCategoriesViewModel(categoriesViewModel: CategoriesViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(BottomNavViewModel::class)]
    fun provideBottomNavViewModel(bottomNavViewModel: BottomNavViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(BagViewModel::class)]
    fun provideCartViewModel(bagViewModel: BagViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(ToolBarViewModel::class)]
    fun provideToolBarViewModel(toolbarViewModel: ToolBarViewModel): ViewModel
}