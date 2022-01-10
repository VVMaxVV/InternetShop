package com.example.internetshop.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.activity.fragments.BaseFragment

open class BaseProductDetailFragment: BaseFragment() {

    companion object {
        const val EXTRA_ID = "id"
    }

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


}