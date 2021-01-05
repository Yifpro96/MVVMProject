package com.aoxing.mymvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aoxing.mymvvm.databinding.ItemHomeArticleBinding
import com.aoxing.mymvvm.model.HomeArticle

class MainAdapter :
    PagingDataAdapter<HomeArticle.Data, HomeArticleViewModel>(HomeArticle.Data.diffCallback) {
    override fun onBindViewHolder(holder: HomeArticleViewModel, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeArticleViewModel {
        val binding = DataBindingUtil.inflate<ItemHomeArticleBinding>(LayoutInflater.from(parent.context),R.layout.item_home_article, parent, false)
        return HomeArticleViewModel(binding)
    }
}

class HomeArticleViewModel(var binding: ItemHomeArticleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HomeArticle.Data) {
        binding.run {
            article = item
            executePendingBindings()
        }
    }
}