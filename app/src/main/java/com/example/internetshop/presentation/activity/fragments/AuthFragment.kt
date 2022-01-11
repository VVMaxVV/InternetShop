package com.example.internetshop.presentation.activity.fragments

import com.example.internetshop.model.data.di.component.AppComponent

class AuthFragment: BaseFragment() {
    override fun inject(component: AppComponent) {
        component.inject(this)
    }

}