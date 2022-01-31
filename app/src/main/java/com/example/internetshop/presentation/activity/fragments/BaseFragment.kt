package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.InternetshopApplication
import com.example.internetshop.presentation.viewModel.MultiViewModuleFactory
import com.example.internetshop.presentation.viewModel.TitleViewModel
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    protected lateinit var factory: MultiViewModuleFactory

//    private val titleViewModel: TitleViewModel by activityViewModels { factory }

    private val titleViewModel: TitleViewModel by lazy {
        ViewModelProvider(requireActivity(), factory).get(TitleViewModel::class.java)
    }

    abstract fun inject(component: AppComponent)

    abstract fun getTitle(): String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inject(getAppComponent())
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleViewModel.titleLiveData.value = getTitle()
    }

    private fun getAppComponent(): AppComponent {
        return (requireActivity().applicationContext as InternetshopApplication)
            .appComponent
    }

    protected fun showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), text, duration).show()
    }
}