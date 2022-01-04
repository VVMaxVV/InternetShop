package com.example.internetshop.model.data.di.component

import android.content.Context
import com.example.internetshop.model.data.di.module.*
import com.example.internetshop.presentation.activity.AuthenticationActivity
import com.example.internetshop.presentation.activity.MainActivity
import com.example.internetshop.presentation.activity.ProductsActivity
import com.example.internetshop.presentation.activity.fragments.FavoriteListFragment
import com.example.internetshop.presentation.activity.fragments.ProductDetailsFragment
import com.example.internetshop.presentation.activity.fragments.ProductsListFragment
import com.example.internetshop.presentation.activity.fragments.ReviewFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    AuthModule::class,
    ViewModelModule::class,
    ProductRepositoryModule::class,
    ReviewModule::class,
    DBModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
    fun inject(mainActivity: MainActivity)
    fun inject(authenticationActivity: AuthenticationActivity)
    fun inject(productsActivity: ProductsActivity)
    fun inject(productDetailsFragment: ProductDetailsFragment)
    fun inject(productsListFragment: ProductsListFragment)
    fun inject(reviewFragment: ReviewFragment)
    fun inject(favoriteListFragment: FavoriteListFragment)
}