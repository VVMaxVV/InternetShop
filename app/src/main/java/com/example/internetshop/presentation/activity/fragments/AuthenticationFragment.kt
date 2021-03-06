package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.internetshop.R
import com.example.internetshop.databinding.FragmentAuthBinding
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.viewModel.AuthenticationViewModel
import java.util.*

class AuthenticationFragment : BaseFragment() {

    private var binding: FragmentAuthBinding? = null

    val viewModel: AuthenticationViewModel by viewModels { factory }

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun getBottomNavVisibility(): Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentAuthBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            viewModel = this@AuthenticationFragment.viewModel
            lifecycleOwner = this@AuthenticationFragment
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.events.observe(viewLifecycleOwner, {
            when (it) {
                is AuthenticationViewModel.AuthenticationEvent.OpenProductListAuthenticationEvent
                -> openProductList()
                is AuthenticationViewModel.AuthenticationEvent.ToastAuthenticationEvent
                -> showToast(
                    requireActivity().resources.getString(it.textId)
                )
                is AuthenticationViewModel.AuthenticationEvent.ServerNotResponseEvent
                -> showToast(
                    requireContext().resources.getString(R.string.label_server_not_response)
                )
            }
        })
        viewModel.onScreenStart()
    }

    private fun openProductList() {
        val action =
            AuthenticationFragmentDirections.actionAuthenticationFragmentToTabShop()
        findNavController().navigate(action)
    }
}