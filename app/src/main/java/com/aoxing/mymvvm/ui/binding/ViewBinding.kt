package com.aoxing.mymvvm.ui.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.aoxing.mymvvm.R
import com.aoxing.mymvvm.common.Rounded_Corner
import com.aoxing.mymvvm.model.HomeArticle
import com.aoxing.mymvvm.refresh
import com.aoxing.mymvvm.ui.square.SquareAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout

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

@BindingAdapter("bindLoading")
fun bindingLoading(refreshLayout: SmartRefreshLayout, isLoading: Boolean) {
    refreshLayout.refresh = isLoading
}

@BindingAdapter("adapterList")
fun bindAdapterList(recyclerView: RecyclerView, data: List<HomeArticle.Data>?) {
    val adapter = recyclerView.adapter as? SquareAdapter
        ?: throw RuntimeException(" adapter is null")
    if ((recyclerView.adapter as SquareAdapter).currentList.isEmpty()) {
        data?.let { adapter.submitList(it, SubmitListCallback(recyclerView)) }
    } else {
        data?.let { adapter.submitList(it) }
    }
}

class SubmitListCallback(val recyclerView: RecyclerView) : Runnable {
    override fun run() {
        recyclerView.scrollToPosition(0)
        recyclerView.requestFocus()
    }
}
