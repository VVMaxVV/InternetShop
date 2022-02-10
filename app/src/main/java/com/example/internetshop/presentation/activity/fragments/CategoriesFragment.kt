package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
        binding?.viewModel = viewModel
        val adapter = CategoryAdapter()
        recyclerView?.let {
            it.addItemDecoration(
                VerticalSpaceItemDecoration(
                    context
                        ?.resources
                        ?.getDimension(R.dimen.dimen_item_category_margin_between_cards)
                        ?.toInt() ?: 0
                )
            )
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
        }

        viewModel.categoriesLiveData.observe(viewLifecycleOwner, {
            adapter.addData(it)
        })
        viewModel.getCategory()

        viewModel.navEventLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is CategoriesViewModel.CategoryEvent.OpenCategoryProductListEvent
                -> openProducts(it.categoryName)
                is CategoriesViewModel.CategoryEvent.ToastCategoryEvent
                -> showToast(it.text)
            }
        })
    }

    private fun openProducts(categoryName: String) {
        val action =
            CategoriesFragmentDirections
                .actionCategoriesFragmentToProductsFromCategoryFragment(categoryName)
        findNavController().navigate(action)
    }
}