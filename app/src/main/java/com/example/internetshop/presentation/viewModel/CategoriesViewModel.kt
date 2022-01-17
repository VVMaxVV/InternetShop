package com.example.internetshop.presentation.viewModel

import com.example.internetshop.presentation.adapters.CategoryAdapter
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(): BaseViewModel() {
    val adapter = CategoryAdapter {

    }
}