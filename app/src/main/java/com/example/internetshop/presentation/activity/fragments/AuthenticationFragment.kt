package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.internetshop.databinding.FragmentAuthBinding
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.viewModel.AuthenticationViewModel

class AuthenticationFragment: BaseFragment() {

    private var binding : FragmentAuthBinding? = null

    val viewModel: AuthenticationViewModel by viewModels { factory }

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.textResult?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity,it,Toast.LENGTH_LONG).show()
        })
    }
}