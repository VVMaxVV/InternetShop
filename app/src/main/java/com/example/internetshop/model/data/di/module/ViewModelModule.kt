package com.example.internetshop.model.data.di.module

import androidx.lifecycle.ViewModel
import com.example.internetshop.presentation.ViewModelKey
import com.example.internetshop.presentation.viewModel.AuthenticationActivityViewModel
import com.example.internetshop.presentation.viewModel.MainActivityViewModel
import com.example.internetshop.presentation.viewModel.ProductsActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @[IntoMap ViewModelKey(MainActivityViewModel::class)]
    fun provideMainActivityViewModel(mainViewModel: MainActivityViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(AuthenticationActivityViewModel::class)]
    fun provideAuthViewModel(authViewModule: AuthenticationActivityViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(ProductsActivityViewModel::class)]
    fun provideProductsActivityViewModule(authViewModule: ProductsActivityViewModel): ViewModel



}