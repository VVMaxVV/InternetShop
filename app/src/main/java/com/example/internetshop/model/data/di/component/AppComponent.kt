package com.example.internetshop.model.data.di.component

import android.content.Context
import com.example.internetshop.data.di.module.CategoryRepositoryModule
import com.example.internetshop.data.di.module.DBModule
import com.example.internetshop.data.di.module.ProductRepositoryModule
import com.example.internetshop.data.di.module.ReviewModule
import com.example.internetshop.domain.di.module.ProductsCategoryUseCaseModule
import com.example.internetshop.domain.di.module.usecase.*
import com.example.internetshop.model.data.di.module.*
import com.example.internetshop.presentation.activity.MainActivity
import com.example.internetshop.presentation.activity.fragments.*
import com.example.internetshop.presentation.service.FetchFavoritesService
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
        FavoritesUseCaseModule::class,
        CategoryModule::class,
        CategoryUseCaseModule::class,
        AuthUseCaseModule::class,
        TokenUseCaseModule::class,
        RepositoriesModule::class,
        CategoryRepositoryModule::class,
        ProductsCategoryUseCaseModule::class,
        GetBagProductsUseCaseModule::class,
        ProductColorsUseCaseModule::class,
        ProductSizesUseCaseModule::class,
        AddProductToBagUseCaseModule::class,
        UpdateBagProductUseCaseModule::class,
        DeleteFromBagUseCaseModule::class,
        ProductsCategoryUseCaseModule::class,
        GetBagProductsUseCaseModule::class,
        ProductColorsUseCaseModule::class,
        ProductSizesUseCaseModule::class,
        AddProductToBagUseCaseModule::class,
        UpdateBagProductUseCaseModule::class,
        DeleteFromBagUseCaseModule::class,
        ProductsCategoryUseCaseModule::class,
        ProductFromServerUseCaseModule::class,
        ProductsFromServerUseCaseModule::class,
        ProductInDBUseCaseModule::class,
        AddToFavoriteUseCaseModule::class,
        DeleteFromFavoriteUseCaseModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(productDetailsFragment: ProductDetailsFragment)
    fun inject(reviewFragment: ReviewFragment)
    fun inject(favoriteListFragment: FavoriteListFragment)
    fun inject(authenticationFragment: AuthenticationFragment)
    fun inject(baseFragment: BaseFragment)
    fun inject(productsListFragment: ProductsListFragment)
    fun inject(bagFragment: BagFragment)
    fun inject(categoriesFragment: CategoriesFragment)
    fun inject(fetchFavoritesService: FetchFavoritesService)
}