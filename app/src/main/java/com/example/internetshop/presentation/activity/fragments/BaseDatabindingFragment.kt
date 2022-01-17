package com.example.internetshop.presentation.activity.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.internetshop.presentation.viewModel.MultiViewModuleFactory
import javax.inject.Inject

abstract class BaseDatabindingFragment<V:ViewModel>(private val aClass: Class<V>) :Fragment() {

    @Inject
    protected lateinit var factory: MultiViewModuleFactory

//    protected val viewModel by lazy {
//        createVm()
//    }



}