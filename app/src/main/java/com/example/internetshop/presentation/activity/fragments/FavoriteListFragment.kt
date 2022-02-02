package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internetshop.R
import com.example.internetshop.databinding.FragmentFavoriteListBinding
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.activity.ContainerHolder
import com.example.internetshop.presentation.adapters.SimpleProductsAdapter
import com.example.internetshop.presentation.viewModel.FavoriteListViewModel

class FavoriteListFragment: BaseFragment() {

    private var binding : FragmentFavoriteListBinding? = null

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

    override fun getTitle(): String = context?.resources?.getString(R.string.favorite)?:""

    override fun getHomeVisibility(): Boolean = true

    override fun getIsScrollingView(): Boolean = true

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
        viewModel.openDetailsEvent.observe(viewLifecycleOwner) {
            openDetails(it)
        }
        viewModel.productsLiveData.observe(viewLifecycleOwner, {
            adapter.productList.clear()
            adapter.productList.addAll(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.getProductsList()
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