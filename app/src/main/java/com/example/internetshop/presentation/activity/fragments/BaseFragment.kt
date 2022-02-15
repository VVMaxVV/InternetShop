package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.internetshop.R
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.InternetshopApplication
import com.example.internetshop.presentation.activity.MainActivity
import com.example.internetshop.presentation.viewModel.BottomNavViewModel
import com.example.internetshop.presentation.viewModel.MultiViewModuleFactory
import com.example.internetshop.presentation.viewModel.ToolBarViewModel
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    protected lateinit var factory: MultiViewModuleFactory

    private val bottomNavViewModel: BottomNavViewModel by lazy {
        ViewModelProvider(requireActivity(), factory).get(BottomNavViewModel::class.java)
    }

    private val toolBarViewModel: ToolBarViewModel by lazy {
        ViewModelProvider(requireActivity(), factory).get(ToolBarViewModel::class.java)
    }

    open fun getBottomNavVisibility(): Boolean = true

    abstract fun inject(component: AppComponent)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inject(getAppComponent())
        setBottomNavIcon()
        bottomNavViewModel.visibility.value = getBottomNavVisibility()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    open fun setBottomNavIcon() {
        if (toolBarViewModel
                .topLevelDestinations
                .contains(findNavController().currentDestination?.id)
                .not()
        ) {
            (context as MainActivity).binding?.toolbar?.setNavigationIcon(R.drawable.ic_back_arrow)
        }
    }

    private fun getAppComponent(): AppComponent {
        return (requireActivity().applicationContext as InternetshopApplication)
            .appComponent
    }

    protected fun showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), text, duration).show()
    }
}