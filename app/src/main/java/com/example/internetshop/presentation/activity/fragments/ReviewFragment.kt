package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internetshop.R
import com.example.internetshop.databinding.FragmentReviewBinding
import com.example.internetshop.model.data.di.component.AppComponent
import com.example.internetshop.presentation.adapters.ReviewsAdapter
import com.example.internetshop.presentation.viewModel.ReviewViewModel

class ReviewFragment : BaseFragment() {
    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun getTitle(): String = context?.resources?.getString(R.string.label_reviews) ?: ""

    override fun getHomeVisibility(): Boolean = true

    override fun getIsScrollingView(): Boolean = true

    private val viewModel: ReviewViewModel by viewModels { factory }

    private var binding: FragmentReviewBinding? = null

    companion object {
        const val EXTRA_ID_REVIEW = "id"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding?.recyclerViewReview
        val adapter = ReviewsAdapter()

        recyclerView?.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.reviewsList.observe(viewLifecycleOwner) {
            adapter.reviews.addAll(it)
            adapter.notifyDataSetChanged()
        }

        val argument = arguments?.getString(EXTRA_ID_REVIEW)
        if (argument == null) findNavController().popBackStack()
        else viewModel.getReviews(argument)
    }
}