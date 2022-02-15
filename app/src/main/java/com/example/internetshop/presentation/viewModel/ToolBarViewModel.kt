package com.example.internetshop.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internetshop.R
import javax.inject.Inject

class ToolBarViewModel @Inject constructor(): ViewModel() {
    val topLevelDestinations = setOf(
        R.id.authenticationFragment,
        R.id.categoriesFragment,
        R.id.favoriteListFragment,
        R.id.cartFragment
    )
    val expanding = MutableLiveData<Boolean>()
}