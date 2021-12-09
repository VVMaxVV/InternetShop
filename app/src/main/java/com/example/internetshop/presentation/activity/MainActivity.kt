package com.example.internetshop.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.internetshop.databinding.ActivityMainBinding
import com.example.internetshop.model.implementation.ProductRepositoryServerImpl
import com.example.internetshop.presentation.ViewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    var viewModel: MainActivityViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //val repository = RepositoryImpl()
        val repository = ProductRepositoryServerImpl()
        viewModel = MainActivityViewModel(repository)

        viewModel?.productLiveData?.observe(this, Observer { product ->
            binding.brand.text = product.brand
            binding.price.text = "${product.prise}$"
            binding.shortDescription.text = product.shortDescription
            binding.description.text = product.description
            binding.numberOfReviews.text = "(${product.numberOfReviews})"
            binding.rating.rating = product.rating
        })
        viewModel?.getProduct()

    }
}