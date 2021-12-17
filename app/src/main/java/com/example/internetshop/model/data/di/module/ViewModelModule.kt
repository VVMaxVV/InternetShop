package com.example.internetshop.model.data.di.module

import androidx.lifecycle.ViewModel
import com.example.internetshop.presentation.ViewModel.AuthenticationActivityViewModel
import com.example.internetshop.presentation.ViewModel.MainActivityViewModel
import com.example.internetshop.presentation.ViewModel.ProductsActivityViewModel
import com.example.internetshop.presentation.ViewModelKey
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