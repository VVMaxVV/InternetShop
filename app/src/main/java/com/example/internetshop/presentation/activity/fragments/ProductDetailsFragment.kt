package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.internetshop.R
import com.example.internetshop.databinding.FragmentProductDetailsBinding
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.viewModel.ProductDetailsViewModel
import com.example.internetshop.presentation.viewModel.ToolBarViewModel


class ProductDetailsFragment :
    BaseFragment() {

    private val toolBarViewModel: ToolBarViewModel by activityViewModels { factory }

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
        binding?.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        toolBarViewModel.expanded.value = false
        return binding?.root
    }

    override fun getExpandedAppBar(): Boolean = false

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reviewButton = binding?.goToReview

        viewModel.product.observe(viewLifecycleOwner, { product ->
            binding?.let {
                it.product = product
            }
        })
        val productId = this.requireArguments().getString(EXTRA_ID)!!
        viewModel.getProduct(productId)

        reviewButton?.setOnClickListener {
            findNavController().navigate(
                ProductDetailsFragmentDirections.actionProductDetailsFragmentToReviewFragment(
                    productId
                )
            )
        }
        listenSpinnersData()

        viewModel.toastEventLiveData.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.favoriteProductsLiveData.observe(viewLifecycleOwner, {
            Log.i(ProductDetailsFragment::class.java.name, "Title: ${it[0].title}")
        })
        viewModel.event.observe(viewLifecycleOwner, {
            when (it) {
                is ProductDetailsViewModel.Event.OpenReview -> openReview(productId)
                is ProductDetailsViewModel.Event.AddToFavorite -> {
                    if (it.value) viewModel.addToFavorite()
                    else viewModel.deleteFromFavorite()
                }
                is ProductDetailsViewModel.Event.ShowToast -> showToast(it.text)
                is ProductDetailsViewModel.Event.ProductNotFound -> showToast(
                    requireContext().resources.getString(
                        R.string.toast_product_not_found
                    )
                )
                is ProductDetailsViewModel.Event.NotAllFieldsAreFilled -> showToast(
                    requireContext().resources.getString(
                        R.string.toast_not_all_fields_are_filled
                    )
                )
                is ProductDetailsViewModel.Event.AddedToCard -> showToast(
                    requireContext().resources.getString(
                        R.string.added_to_cart
                    )
                )
            }
        })
    }

    private fun listenSpinnersData() {
        viewModel.sizesSpinnerState.list.observe(this, { sizesList ->
            binding?.sizeSpinner?.adapter =
                context?.let {
                    ArrayAdapter(
                        it,
                        R.layout.item_spinner_pdp,
                        mutableListOf(
                            requireContext().resources.getString(R.string.label_pdp_size)
                        ).apply {
                            addAll(sizesList)
                        })
                }
        })
        viewModel.colorsSpinnerState.list.observe(this, { colorsList ->
            binding?.colorSpinner?.adapter =
                context?.let {
                    ArrayAdapter(
                        it,
                        R.layout.item_spinner_pdp,
                        mutableListOf(
                            requireContext().resources.getString(R.string.label_pdp_color)
                        ).apply {
                            addAll(colorsList)
                        }
                    )
                }
        })
        viewModel.getSpinnerEntries()
    }

    private fun openReview(id: String) {
        findNavController().navigate(
            ProductDetailsFragmentDirections.actionProductDetailsFragmentToReviewFragment(
                id
            )
        )
    }
}