package com.example.internetshop.presentation

import android.app.Application
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.model.data.di.component.DaggerAppComponent

class InternetshopApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}