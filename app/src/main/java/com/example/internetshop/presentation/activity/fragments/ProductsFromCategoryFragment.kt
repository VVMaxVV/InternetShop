package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internetshop.databinding.FragmentCategoryProductListBinding
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.activity.ContainerHolder
import com.example.internetshop.presentation.adapters.ProductsAdapter
import com.example.internetshop.presentation.adapters.VerticalSpaceItemDecoration
import com.example.internetshop.presentation.viewModel.ProductListViewModel
import java.util.*

class ProductsFromCategoryFragment : BaseFragment() {

    private var binding: FragmentCategoryProductListBinding? = null

    private val productListViewModel: ProductListViewModel by viewModels { factory }

    companion object {
        const val EXTRA_CATEGORY_NAME = "category"
    }

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun getTitle(): String =
        this.requireArguments().getString(
            EXTRA_CATEGORY_NAME
        ).toString()
            .replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                else it.toString()
            }


    override fun getHomeVisibility(): Boolean = true

    override fun getIsScrollingView(): Boolean = true

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

        productListViewModel.productsList.observe(requireActivity(), {
            adapter.addData(it)
        })
        val categoryName = this.requireArguments().getString(EXTRA_CATEGORY_NAME)

        if (categoryName == null) requireActivity().supportFragmentManager.popBackStack()
        else productListViewModel.getCategoryProductList(categoryName)


        productListViewModel.navEventLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is ProductListViewModel.Event.OpenProductDetailEvent -> openDetails(it.id)
                is ProductListViewModel.Event.ToastEvent -> showToast(it.text)
            }
        })
    }

    private fun openDetails(id: String) {
        (requireActivity() as? ContainerHolder)?.let {
            val fragment = ProductDetailsFragment().apply {
                this.arguments = bundleOf(ProductDetailsFragment.EXTRA_ID to id)
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(
                    it.getContainerId()
                        ?: throw IllegalStateException("Container id must not be null"), fragment
                )
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}