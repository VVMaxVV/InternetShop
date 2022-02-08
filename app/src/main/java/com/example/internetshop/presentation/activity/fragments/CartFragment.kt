package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.internetshop.databinding.FragmentCartBinding
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.viewModel.CartViewModel

class CartFragment : BaseFragment() {
    private var binding: FragmentCartBinding? = null

    private val cartViewModel: CartViewModel by viewModels { factory }

    override fun setBottomNavVisibility(): Boolean = true

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCartBinding.inflate(
            inflater,
            container,
            false
        )
        return binding?.root
    }
}