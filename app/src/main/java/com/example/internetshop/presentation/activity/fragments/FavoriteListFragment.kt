package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internetshop.databinding.FragmentFavoriteListBinding
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.adapters.SimpleProductsAdapter
import com.example.internetshop.presentation.viewModel.FavoriteListViewModel

class FavoriteListFragment : BaseFragment() {

    private var binding: FragmentFavoriteListBinding? = null

    val viewModel: FavoriteListViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding?.recyclerViewProducts
        val adapter = SimpleProductsAdapter {
            viewModel.onProductClicked(it)
        }
        recyclerView.let {
            it?.adapter = adapter
            it?.layoutManager = LinearLayoutManager(requireContext())
            it?.setHasFixedSize(true)
        }
        viewModel.productsLiveData.observe(viewLifecycleOwner, {
            adapter.addData(it)
        })
        viewModel.getProductsList()
        viewModel.event.observe(viewLifecycleOwner, {
            when (it) {
                is FavoriteListViewModel.Event.OpenProductDetailEvent -> openDetails(it.id, it.productName)
                is FavoriteListViewModel.Event.ToastEvent -> showToast(it.text)
            }
        })
    }

    private fun openDetails(id: String, productName: String) {
        findNavController().navigate(
            FavoriteListFragmentDirections.actionFavoriteListFragmentToProductDetailsFragment(
                id, productName
            )
        )
    }
}