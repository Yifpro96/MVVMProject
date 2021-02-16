package com.aoxing.mymvvm.ui.square

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aoxing.mymvvm.model.HomeArticle
import com.aoxing.mymvvm.model.doFailure
import com.aoxing.mymvvm.model.doSuccess
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class SquareViewModel(private val repository: SquareRepository) : ViewModel() {

    val loading = ObservableBoolean()
    private val _failure = MutableLiveData<String>()
    val failure = _failure
    val liveData: MutableLiveData<MutableList<HomeArticle.Data>> by lazy { MutableLiveData<MutableList<HomeArticle.Data>>() }

    fun fetchSquares(page: Int = 0) = liveData {
        repository.fetchSquares(page)
            .onStart { loading.set(true) }
            .onCompletion { loading.set(false) }
            .collectLatest { result ->
                result.doSuccess {
                    emit(it)
                }
                result.doFailure {
                    _failure.value = it?.message ?: "failure"
                }
            }
    }

    fun fetchBanner() = liveData {
        repository.fetchBanner()
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