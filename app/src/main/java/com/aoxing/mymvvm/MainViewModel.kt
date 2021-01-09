package com.aoxing.mymvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aoxing.mymvvm.model.HomeArticle
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val repository: HomeRepository) : ViewModel() {
    fun fetchHomeArticle()=repository.fetchHomeArticle().cachedIn(viewModelScope).asLiveData()
}