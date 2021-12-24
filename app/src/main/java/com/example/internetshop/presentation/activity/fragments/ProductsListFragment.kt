package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internetshop.databinding.FragmentProductsListBinding
import com.example.internetshop.presentation.InternetshopApplication
import com.example.internetshop.presentation.MultiViewModulFactory
import com.example.internetshop.presentation.activity.ContainerHolder
import com.example.internetshop.presentation.adapter.SimpleProductsAdapter
import com.example.internetshop.presentation.viewModel.ProductsActivityViewModel
import javax.inject.Inject

class ProductsListFragment: Fragment() {
    @Inject
    lateinit var factory: MultiViewModulFactory

    val viewModel : ProductsActivityViewModel by viewModels { factory }

    private var binding: FragmentProductsListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsListBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().applicationContext as InternetshopApplication)
            .appComponent
            .inject(this)

        val recyclerView = binding?.recyclerViewProducts
        val adapter = SimpleProductsAdapter {
            viewModel.onProductClicked(it)
        }
        recyclerView?.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
        }

        viewModel.productsList.observe(viewLifecycleOwner) {
            adapter.productList.addAll(it)
            adapter.notifyDataSetChanged()
        }
        viewModel.getProductsRx()
        viewModel.openDetailsEvent.observe(viewLifecycleOwner) {
            openDetails(it)
        }
    }

    private fun openDetails(id: String) {
        (requireActivity() as? ContainerHolder)?.let {
            val fragment = ProductDetailsFragment().apply {
                this.arguments = bundleOf(ProductDetailsFragment.EXTRA_ID to id)
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(it.getContainerId()?:throw IllegalStateException("Container id must not be null"),fragment)
                .commit()
        }
    }
}