package com.aoxing.mymvvm.ui.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.aoxing.mymvvm.model.doFailure
import com.aoxing.mymvvm.model.doSuccess
import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    val loading = ObservableBoolean()
    private val _failure = MutableLiveData<String>()
    val failure = _failure

    fun fetchHomeData() = repository.fetchHomeData().cachedIn(viewModelScope).asLiveData()

    fun fetchBanner() = liveData {
        repository.fetchBanner()
            .onStart { Logger.e("fetchBanner onStart") }
            .onCompletion { Logger.e("fetchBanner onCompletion") }
            .catch { Logger.e("fetchBanner catch -> ${it.message}") }
            .collectLatest { result ->
                result.doSuccess {
                    emit(it)
                }
                result.doFailure {
                    _failure.value = it?.message ?: "failure"
                }
            }
    }
}