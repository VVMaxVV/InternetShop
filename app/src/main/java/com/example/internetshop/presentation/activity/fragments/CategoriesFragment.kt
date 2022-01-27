package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internetshop.R
import com.example.internetshop.databinding.FragmentCategoriesBinding
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.adapters.CategoryAdapter
import com.example.internetshop.presentation.adapters.VerticalSpaceItemDecoration
import com.example.internetshop.presentation.viewModel.CategoriesViewModel

class CategoriesFragment : BaseFragment() {

    val viewModel: CategoriesViewModel by viewModels { factory }

    private var binding: FragmentCategoriesBinding? = null

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun getTitle(): String {
        return context?.resources?.getString(R.string.categories)?:""
    }

    override fun getHomeVisibility(): Boolean {
        return true
    }

    override fun getIsScrollingView(): Boolean {
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCategoriesBinding.inflate(
            inflater,
            container,
            false
        )

        binding?.viewModel = viewModel
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding?.recyclerViewCategory
        val adapter = CategoryAdapter {
            CategoriesViewModel.CategoryEvent.OpenCategoryProductListEvent(it.name)
        }
        recyclerView?.let {
            it.addItemDecoration(VerticalSpaceItemDecoration(32))
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
        }

        viewModel.navEventLiveData.observe(requireActivity(), {
            when (it) {
                is CategoriesViewModel.CategoryEvent.OpenCategoryProductListEvent -> openCategory()
                is CategoriesViewModel.CategoryEvent.ToastCategoryEvent -> showToast(it.text)
            }
        })

        viewModel.categoriesLiveData.observe(requireActivity(), {
            adapter.addData(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.getCategory()
    }

    private fun openCategory() {
        showToast("Go to next fragment")
    }


}