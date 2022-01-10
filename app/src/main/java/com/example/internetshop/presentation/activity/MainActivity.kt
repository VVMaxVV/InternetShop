package com.example.internetshop.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.internetshop.data.cache.InternetShopDB
import com.example.internetshop.databinding.ActivityMainBinding
import com.example.internetshop.presentation.activity.fragments.ProductsListFragment
import com.example.internetshop.presentation.viewModel.MultiViewModuleFactory
import com.example.internetshop.presentation.viewModel.ProductDetailsViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity(), ContainerHolder {
    @Inject
    lateinit var factory: MultiViewModuleFactory

    val viewModel :ProductDetailsViewModel by viewModels { factory }

    var binding: ActivityMainBinding? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val db = Room.databaseBuilder(
            applicationContext,
            InternetShopDB::class.java,
            "FavoriteProductList"
        )
        binding?.let {
            val fragment = ProductsListFragment()
            supportFragmentManager.beginTransaction()
                .replace(it.fragmentContainer.id,fragment)
                .commit()
        }
    }

    override fun getContainerId(): Int? {
        return binding?.fragmentContainer?.id
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        (applicationContext as InternetshopApplication).appComponent.inject(this)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//        Log.i("Dagger", "$viewModel")
//        viewModel.productLiveData?.observe(this, Observer { product ->
//            binding.brand.text = product.brand
//            binding.price.text = "${product.prise}$"
//            binding.shortDescription.text = product.shortDescription
//            binding.description.text = product.description
//            binding.numberOfReviews.text = "(${product.numberOfReviews})"
//            binding.rating.rating = product.rating
//            Picasso.with(this)
//                .load(product.imageURL)
//                .into(binding.mainImage)
//        })
//        viewModel.getProductRx(intent.getStringExtra(EXTRA_ID)!!)
//    }
    }