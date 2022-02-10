package com.example.internetshop.presentation.bindingAdapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:src")
fun setImage(view: ImageView, imageUrl: String?) {
    Picasso.with(view.context)
        .load(imageUrl)
        .into(view)
}


@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    val visibility = if(value) View.VISIBLE else View.INVISIBLE
    view.visibility = visibility
}