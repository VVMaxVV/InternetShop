//package com.example.internetshop.presentation
//
//import android.content.SharedPreferences
//import com.example.internetshop.model.data.remote.ProductsApi
//import com.example.internetshop.model.implementation.ProductProductRepositoryServerImpl
//import com.example.internetshop.model.interfaces.*
//import dagger.Component
//import dagger.Module
//import retrofit2.Retrofit
//import javax.inject.Inject
//
//class MainViewModel @Inject constructor(
//    private val repository: ProductRepository
//)
//
//class MainActivity{
//    @Inject mainViewMOdel:MainVIewModel
//}
//
//@Component(modules [AppModule])
//interface AppComponent{
//
//}
//
//@Module
//interface AppModule(){
//
//    @Provides
//    fun provideProductRepo(productIMpl:ProductProductRepositoryServerImpl) :ProductRepository{
//        return productIMpl
//    }
//
//    @Provides
//    fun serverImpl(api:ProductsApi): ProductProductRepositoryServerImpl {
//        return ProductProductRepositoryServerImpl(api)
//    }
//
//    @Provides()
//    fun provideProductApi(retofit:Retrofit) :ProductsApi {
//        retofit.create(ProductsApi::class.java)
//    }
//    @Provides
//    fun provideRetrofit() :Retrofit{
//        return Retrofit.Builder()
//            .baseUrl("")
//            .build()
//    }
//
//    @
//}
//
