package com.example.internetshop.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.internetshop.databinding.ActivityMainBinding
import com.example.internetshop.presentation.InternetshopApplication
import com.example.internetshop.presentation.MultiViewModulFactory
import com.example.internetshop.presentation.ViewModel.MainActivityViewModel
import com.squareup.picasso.Picasso
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MultiViewModulFactory

    val viewModel :MainActivityViewModel by viewModels { factory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as InternetshopApplication).appComponent.inject(this)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.i("Dagger", "$viewModel")
        viewModel.productLiveData?.observe(this, Observer { product ->
            binding.brand.text = product.brand
            binding.price.text = "${product.prise}$"
            binding.shortDescription.text = product.shortDescription
            binding.description.text = product.description
            binding.numberOfReviews.text = "(${product.numberOfReviews})"
            binding.rating.rating = product.rating
            Picasso.with(this)
                .load(product.imageURL)
                .into(binding.mainImage)
       })
        viewModel.getProductRx("1")
    }
}