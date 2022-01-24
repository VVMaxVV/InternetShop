package com.example.internetshop.model.data.viewStates

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

open class BaseViewState

@BindingAdapter("image_uri")
fun setImage(view: ImageView, imageUrl: String?) =
    Glide.with(view.context)
        .load(imageUrl).apply(RequestOptions())
        .into(view)