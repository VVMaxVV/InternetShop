package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.internetshop.R
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.InternetshopApplication
import com.example.internetshop.presentation.activity.MainActivity
import com.example.internetshop.presentation.viewModel.BottomNavViewModel
import com.example.internetshop.presentation.viewModel.MultiViewModuleFactory
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    protected lateinit var factory: MultiViewModuleFactory

    private val bottomNavViewModel: BottomNavViewModel by lazy {
        ViewModelProvider(requireActivity(), factory).get(BottomNavViewModel::class.java)
    }

    open fun setBottomNavVisibility(): Boolean = true

    abstract fun inject(component: AppComponent)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inject(getAppComponent())
        (context as MainActivity).binding?.toolbar?.setNavigationIcon(R.drawable.ic_back_arrow)
        bottomNavViewModel.visibility.value = setBottomNavVisibility()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun getAppComponent(): AppComponent {
        return (requireActivity().applicationContext as InternetshopApplication)
            .appComponent
    }

    protected fun showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), text, duration).show()
    }
}