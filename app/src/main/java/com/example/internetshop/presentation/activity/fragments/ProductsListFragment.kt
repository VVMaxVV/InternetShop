package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internetshop.databinding.FragmentCategoryProductListBinding
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.adapters.ProductsAdapter
import com.example.internetshop.presentation.adapters.VerticalSpaceItemDecoration
import com.example.internetshop.presentation.viewModel.ProductsListViewModel

class ProductsListFragment : BaseFragment() {

    private var binding: FragmentCategoryProductListBinding? = null

    private val productsListViewModel: ProductsListViewModel by viewModels { factory }

    companion object {
        const val EXTRA_CATEGORY_NAME = "category"
    }

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCategoryProductListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ProductsAdapter()
        val recyclerView = binding?.recyclerViewProducts
        recyclerView?.let {
            it.addItemDecoration(VerticalSpaceItemDecoration(32))
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
        }

        productsListViewModel.productsList.observe(requireActivity(), {
            adapter.addData(it)
        })
        val categoryName = this.requireArguments().getString(EXTRA_CATEGORY_NAME)

        if (categoryName == null) findNavController().popBackStack()
        else productsListViewModel
            .getCategoryProductList(categoryName)


        productsListViewModel.navEventLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is ProductsListViewModel.Event.OpenProductDetailEvent -> openDetails(it.id, it.productName)
                is ProductsListViewModel.Event.ToastEvent -> showToast(it.text)
            }
        })
    }

    private fun openDetails(id: String, productName: String) {
        val action =
            ProductsListFragmentDirections
                .actionProductsFromCategoryFragmentToProductDetailsFragment(id, productName)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}