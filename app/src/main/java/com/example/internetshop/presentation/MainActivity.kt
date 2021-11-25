package com.example.internetshop.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.internetshop.TEstVm
import com.example.internetshop.databinding.ActivityMainBinding
import com.example.internetshop.model.RepositoryImpl

class MainActivity : AppCompatActivity() {

    private var vm: TEstVm?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val repository = RepositoryImpl()
        val viewModel = MainActivityViewModel(repository)
        viewModel.productLiveData.observe(this, Observer { product ->
            binding.brand.text = product.brand
            binding.price.text = "${product.prise}$"
            binding.shortDescription.text = product.shortDescription
            binding.description.text = product.description
            binding.numberOfReviews.text = "(${product.numberOfReviews})"
        })
        val product = viewModel.getProduct()







//        vm = TEstVm(this)
//        subscribeToViewModelEvents()
//
//        binding.mainImage.setOnClickListener {
//            vm?.login()
//        }
//    }

//    private fun subscribeToViewModelEvents() {
//        vm?.navigationEvents?.observe(this, Observer { token->
//            Toast.makeText(this,token,Toast.LENGTH_SHORT).show()
//        })
    }
}