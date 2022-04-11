package com.example.internetshop.presentation.bindingAdapter

import android.graphics.Color
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.squareup.picasso.Picasso

@BindingAdapter("android:src")
fun setImage(view: ImageView, imageUrl: String?) {
    Picasso.with(view.context)
        .load(imageUrl)
        .into(view)
}


@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    val visibility = if (value) View.VISIBLE else View.GONE
    view.visibility = visibility
}

@BindingAdapter("android:background")
fun setBackgroundColor(view: View, value: Color) {
    view.background = value.toDrawable()
}

@BindingAdapter("android:onItemSelect")
fun onItemSelect(view: View, cursor: MutableLiveData<Int?>?) {
    (view as Spinner).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            cursor?.value = position
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {
            cursor?.value = 0
        }
    }
}

