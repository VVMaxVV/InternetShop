package com.example.internetshop.model.data.di.component

import android.content.Context
import com.example.internetshop.data.di.module.DBModule
import com.example.internetshop.data.di.module.ProductRepositoryModule
import com.example.internetshop.data.di.module.ReviewModule
import com.example.internetshop.domain.di.module.FavoritesUseCaseModule
import com.example.internetshop.model.data.di.module.AppModule
import com.example.internetshop.model.data.di.module.AuthModule
import com.example.internetshop.model.data.di.module.ViewModelModule
import com.example.internetshop.presentation.activity.BaseProductDetailFragment
import com.example.internetshop.presentation.activity.MainActivity
import com.example.internetshop.presentation.activity.ProductsActivity
import com.example.internetshop.presentation.activity.fragments.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        AuthModule::class,
        ViewModelModule::class,
        ProductRepositoryModule::class,
        ReviewModule::class,
        DBModule::class,
        FavoritesUseCaseModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(productsActivity: ProductsActivity)
    fun inject(productDetailsFragment: ProductDetailsFragment)
    fun inject(productsListFragment: ProductsListFragment)
    fun inject(reviewFragment: ReviewFragment)
    fun inject(favoriteListFragment: FavoriteListFragment)
    fun inject(authenticationFragment: AuthenticationFragment)
    fun inject(baseFragment: BaseFragment)
    fun inject(baseProductDetailsFragment: BaseProductDetailFragment)
    fun inject(categoriesFragment: CategoriesFragment)
}