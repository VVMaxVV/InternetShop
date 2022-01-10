package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.InternetshopApplication
import com.example.internetshop.presentation.viewModel.MultiViewModuleFactory
import javax.inject.Inject

abstract class BaseFragment: Fragment() {

    @Inject
    protected lateinit var factory: MultiViewModuleFactory

    abstract fun inject(component: AppComponent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject(getAppComponent())
    }

    private fun getAppComponent(): AppComponent {
        return (requireActivity().applicationContext as InternetshopApplication)
            .appComponent
    }
}