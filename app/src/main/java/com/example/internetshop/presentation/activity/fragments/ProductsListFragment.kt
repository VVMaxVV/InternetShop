package com.example.internetshop.presentation.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.internetshop.R

class ProductsListFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var listItems: MutableList<Int>
        listItems = mutableListOf(1,2,3,4)
        return inflater.inflate((R.layout.fragment_products_list), container, false)
    }
}