package com.example.internetshop.presentation.activity.fragments

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
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
        ).apply {
            viewModel = this@CategoriesFragment.viewModel
            lifecycleOwner = this@CategoriesFragment
        }
        requireContext().registerReceiver(
            networkBroadcast.get(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding?.recyclerViewCategory
        val adapter = CategoryAdapter()
        recyclerView?.let {
            it.addItemDecoration(
                VerticalSpaceItemDecoration(
                    requireContext()
                        .resources
                        .getDimension(R.dimen.dimen_item_category_margin_between_cards)
                        .toInt()
                )
            )
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
        }

        networkBroadcast.get().networkConnection.observe(viewLifecycleOwner, {
            viewModel.getAllElement()
        })

        viewModel.categoriesLiveData.observe(viewLifecycleOwner, {
            adapter.addData(it)
        })

        viewModel.eventLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is CategoriesViewModel.CategoryEvent.OpenCategoryProductListEvent
                -> openProducts(it.categoryName)
                is CategoriesViewModel.CategoryEvent.OpenNotificationEvent
                -> openNotification()
                is CategoriesViewModel.CategoryEvent.ToastCategoryEvent
                -> showToast(it.text)
            }
        })
    }

    private fun openNotification() {
        Log.i("CategoriesFragment", "User clicked on notification")
        showToast(resources.getString(R.string.toast_open_sale_notification))
    }

    private fun openProducts(categoryName: String) {
        val action =
            CategoriesFragmentDirections
                .actionCategoriesFragmentToProductsFromCategoryFragment(categoryName)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireContext().unregisterReceiver(networkBroadcast.get())
    }
}