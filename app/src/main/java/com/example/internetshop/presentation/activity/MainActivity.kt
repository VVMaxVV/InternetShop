package com.example.internetshop.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.internetshop.databinding.ActivityMainBinding
import com.example.internetshop.presentation.activity.fragments.CategoriesFragment
import com.example.internetshop.presentation.viewModel.AuthenticationViewModel
import com.example.internetshop.presentation.viewModel.MultiViewModuleFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity(), ContainerHolder {
    @Inject
    lateinit var factory: MultiViewModuleFactory

    val viewModel: AuthenticationViewModel by viewModels { factory }

    var binding: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.let {
            val fragment = CategoriesFragment()
            supportFragmentManager.beginTransaction()
                .replace(it.fragmentContainer.id, fragment)
                .commit()
        }
    }

    override fun getContainerId(): Int? {
        return binding?.fragmentContainer?.id
    }
}