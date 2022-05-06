package com.example.internetshop.presentation.bindingAdapter

import android.graphics.Color
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.BindingAdapter
import com.example.internetshop.R
import com.example.internetshop.model.data.adapterStates.BaseSpinnerState
import com.squareup.picasso.Picasso

@BindingAdapter("android:src")
fun setImage(view: ImageView, imageUrl: String?) {
    Picasso.with(view.context)
        .load(imageUrl)
        .placeholder(R.drawable.loading_anim)
        .into(view)
}


@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean?) {
    val visibility = if (value == true) View.VISIBLE else View.GONE
    view.visibility = visibility
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: String?) {
    val visibility = if (value?.isEmpty() == false) View.VISIBLE else View.GONE
    view.visibility = visibility
}

@BindingAdapter("android:background")
fun setBackgroundColor(view: View, value: Color) {
    view.background = value.toDrawable()
}

@BindingAdapter("android:onItemSelect")
fun onItemSelect(view: View, cursor: BaseSpinnerState?) {
    (view as Spinner).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            cursor?.position?.value = position
            cursor?.positionValue?.value = parent?.selectedItem.toString()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            cursor?.position?.value = null
            cursor?.positionValue?.value = null
        }
    }
}

