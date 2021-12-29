package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.internetshop.databinding.FragmentProductDetailsBinding
import com.example.internetshop.presentation.InternetshopApplication
import com.example.internetshop.presentation.MultiViewModulFactory
import com.example.internetshop.presentation.activity.ContainerHolder
import com.example.internetshop.presentation.viewModel.MainActivityViewModel
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

    companion object {
        const val EXTRA_ID = "id"
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
        val reviewButton = binding?.goToReview
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
        viewModel.getProductRx(requireArguments()!!.getString(ReviewFragment.EXTRA_ID)!!)

        reviewButton?.setOnClickListener {
            (requireActivity() as? ContainerHolder)?.let {
                val fragment = ReviewFragment().apply {
                    this.arguments = bundleOf(ReviewFragment.EXTRA_ID to id)
                }
                requireActivity().supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(it.getContainerId()?:throw IllegalStateException("Container id must not be null"),fragment)
                    .commit()
            }
        }
    }
}