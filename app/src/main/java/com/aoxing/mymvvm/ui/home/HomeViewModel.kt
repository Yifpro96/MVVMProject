package com.aoxing.mymvvm.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    fun fetchHomeData() = repository.fetchHomeData().cachedIn(viewModelScope).asLiveData()

}