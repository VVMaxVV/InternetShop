package com.example.internetshop.model.data.di.component

import com.example.internetshop.model.data.di.module.AppModule
import com.example.internetshop.model.data.di.module.AuthModule
import com.example.internetshop.model.data.di.module.ProductRepositoryModule
import com.example.internetshop.model.data.di.module.ViewModelModule
import com.example.internetshop.presentation.activity.AuthenticationActivity
import com.example.internetshop.presentation.activity.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, AuthModule::class, ViewModelModule::class, ProductRepositoryModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(authenticationActivity: AuthenticationActivity)
}