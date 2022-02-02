package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.internetshop.R
import com.example.internetshop.data.cache.InternetShopDB
import com.example.internetshop.databinding.FragmentProductDetailsBinding
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.activity.ContainerHolder
import com.example.internetshop.presentation.viewModel.ProductDetailsViewModel
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ProductDetailsFragment : BaseFragment() {
    @Inject
    lateinit var db: InternetShopDB

    val viewModel: ProductDetailsViewModel by viewModels { factory }

    private var binding: FragmentProductDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_product_details,
                container,
                false
            )
        return binding?.root
    }

    companion object {
        const val EXTRA_ID = "id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun getTitle(): String = "Product #${this.requireArguments().getString(EXTRA_ID)}"

    override fun getHomeVisibility(): Boolean = true

    override fun getIsScrollingView(): Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reviewButton = binding?.goToReview
        val favoriteButton = binding?.favorite

        viewModel.productLiveData?.observe(viewLifecycleOwner, { product ->
            binding?.let {
                it.product = product
                Picasso.with(requireContext())
                    .load(product.imageURL)
                    .into(it.mainImage)
            }
        })

        val productId = this.requireArguments().getString(EXTRA_ID)!!
        viewModel.getProductRx(productId)

        reviewButton?.setOnClickListener {
            (requireActivity() as? ContainerHolder)?.let {
                val fragment = ReviewFragment().apply {
                    this.arguments = bundleOf(ReviewFragment.EXTRA_ID_REVIEW to productId)
                }
                requireActivity().supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(
                        it.getContainerId()
                            ?: throw IllegalStateException("Container id must not be null"),
                        fragment
                    )
                    .commit()
            }
        }

        viewModel.toastEventLiveData.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.favoriteProductsLiveData.observe(viewLifecycleOwner, {
            Log.i("123", "Title: ${it[0].title}")
        })

        favoriteButton?.setOnClickListener {
            viewModel.addToFavorite()
            viewModel.getFromFavorite()
        }
    }
}