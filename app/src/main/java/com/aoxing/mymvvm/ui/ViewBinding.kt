package com.aoxing.mymvvm.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.aoxing.mymvvm.R
import com.aoxing.mymvvm.Rounded_Corner

@BindingAdapter("bindingSrc")
fun bindingSrc(view: ImageView, url: String) {
    view.run {
        scaleType = ImageView.ScaleType.CENTER_CROP
        if (url.isEmpty()) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
            val corner = Rounded_Corner.toFloat()
            load(url) {
                crossfade(true)
                placeholder(R.mipmap.ic_placeholder)
                transformations(
                    RoundedCornersTransformation(
                        topLeft = corner,
                        bottomLeft = corner,
                        topRight = corner,
                        bottomRight = corner
                    )
                )
            }
        }
    }
}