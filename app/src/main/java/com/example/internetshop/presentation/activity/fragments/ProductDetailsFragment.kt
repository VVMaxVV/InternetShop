package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.internetshop.databinding.FragmentProductDetailsBinding
import com.example.internetshop.presentation.InternetshopApplication
import com.example.internetshop.presentation.MultiViewModulFactory
import com.example.internetshop.presentation.ViewModel.MainActivityViewModel
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ProductDetailsFragment: Fragment() {
    @Inject
    lateinit var factory: MultiViewModulFactory

    val viewModel : MainActivityViewModel by viewModels { factory }

    private var binding: FragmentProductDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
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
        viewModel.productLiveData?.observe(viewLifecycleOwner, Observer { product ->
            binding?.let {
                it.brand.text = product.brand
                it.price.text = "${product.prise}$"
                it.shortDescription.text = product.shortDescription
                it.description.text = product.description
                it.numberOfReviews.text = "(${product.numberOfReviews})"
                it.rating.rating = product.rating
                Picasso.with(requireContext())
                    .load(product.imageURL)
                    .into(it.mainImage)
            }
        })
        viewModel.getProductRx("2")
    }
}