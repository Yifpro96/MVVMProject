package com.aoxing.mymvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn

class MainViewModel(private val repository: HomeRepository) : ViewModel() {
    fun fetchTopArticle()=repository.fetchTopArticle().asLiveData()
    fun fetchArticles()=repository.fetchArticles().cachedIn(viewModelScope).asLiveData()
}