package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internetshop.R
import com.example.internetshop.databinding.FragmentBagBinding
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.adapters.BagProductAdapter
import com.example.internetshop.presentation.adapters.VerticalSpaceItemDecoration
import com.example.internetshop.presentation.viewModel.BagViewModel

class BagFragment : BaseFragment() {
    private var binding: FragmentBagBinding? = null

    private var adapter: BagProductAdapter? = null

    private val viewModel: BagViewModel by viewModels { factory }

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentBagBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            viewModel = this@BagFragment.viewModel
            lifecycleOwner = this@BagFragment
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding?.recyclerView
        adapter = BagProductAdapter()
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
        listenEvents()
        listenProductList()
    }

    private fun listenProductList() {
        viewModel.productList.observe(this, {
            adapter?.add(it)
        })
        viewModel.getProducts()
    }

    private fun listenEvents() {
        viewModel.events.observe(this, {
            when (it) {
                is BagViewModel.Event.OpenProduct ->
                    openProduct(it.id, it.productName)
                is BagViewModel.Event.DeleteFromBag ->
                    adapter?.delete(it.position)
            }
        })
    }

    private fun openProduct(id: String, productName: String) {
        val action =
            BagFragmentDirections.actionBagFragmentToProductDetailsFragment(id, productName)
        findNavController().navigate(action)
    }
}