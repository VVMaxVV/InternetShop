package com.example.internetshop.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internetshop.R
import com.example.internetshop.databinding.ActivityProductsBinding
import com.example.internetshop.presentation.InternetshopApplication
import com.example.internetshop.presentation.activity.fragments.ProductDetailsFragment
import com.example.internetshop.presentation.adapters.SimpleProductsAdapter
import com.example.internetshop.presentation.viewModel.MultiViewModuleFactory
import com.example.internetshop.presentation.viewModel.ProductsActivityViewModel
import javax.inject.Inject


class ProductsActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MultiViewModuleFactory

    val viewModel: ProductsActivityViewModel by viewModels {factory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        (applicationContext as InternetshopApplication).appComponent.inject(this)
        val binding = ActivityProductsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val recyclerView = binding.recyclerViewProducts
        val adapter = SimpleProductsAdapter {
            openDetails(it.id)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        viewModel.productsList.observe(this) {
            adapter.productList.addAll(it)
            adapter.notifyDataSetChanged()
        }
        viewModel.getProductsRx()
    }

    fun openDetails(id: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(ProductDetailsFragment.EXTRA_ID, id)
        }
        startActivity(intent)
    }
}