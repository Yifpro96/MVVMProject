package com.aoxing.mymvvm.ui.square

import android.view.View
import com.aoxing.mymvvm.R
import com.aoxing.mymvvm.databinding.HeaderSquareBannerBinding
import com.aoxing.mymvvm.databinding.ItemHomeArticleBinding
import com.aoxing.mymvvm.model.HomeArticle
import com.hi.dhl.jdatabinding.DataBindingListAdapter
import com.hi.dhl.jdatabinding.DataBindingViewHolder

class SquareAdapter : DataBindingListAdapter<HomeArticle.Data>(HomeArticle.Data.diffCallback) {

    override fun layout(position: Int) = when (position) {
        0 -> R.layout.header_square_banner
        else -> R.layout.item_home_article
    }

    override fun viewHolder(layout: Int, view: View) = when (layout) {
        R.layout.header_square_banner -> SquareHeaderViewModel(headerView!!)
        else -> SquareArticleViewModel(view)
    }

    override fun getItemCount() = super.getItemCount()

    var headerView: View? = null
}

class SquareHeaderViewModel(view:View):DataBindingViewHolder<HomeArticle.Data>(view){

    private val mBinding by viewHolderBinding<HeaderSquareBannerBinding>(view)

    override fun bindData(data: HomeArticle.Data, position: Int) {
    }
}

class SquareArticleViewModel(view: View) : DataBindingViewHolder<HomeArticle.Data>(view) {

    private val mBinding by viewHolderBinding<ItemHomeArticleBinding>(view)

    override fun bindData(data: HomeArticle.Data, position: Int) {
        mBinding.apply {
            article = data
            executePendingBindings()
        }
    }

}