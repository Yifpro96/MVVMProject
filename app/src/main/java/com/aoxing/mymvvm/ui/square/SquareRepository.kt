package com.aoxing.mymvvm.ui.square

import com.aoxing.mymvvm.model.HomeArticle
import com.aoxing.mymvvm.model.YifResult
import com.aoxing.mymvvm.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SquareRepository(val api: ApiService) {

    fun fetchSquares(page: Int): Flow<YifResult<HomeArticle>> {
        return flow {
            try {
                emit(YifResult.Success(api.fetchSquares(page).data ?: HomeArticle()))
            } catch (e: Exception) {
                emit(YifResult.Failure(e))
            }
        }
    }

    fun fetchBanner(): Flow<YifResult<List<HomeArticle.Data>>> {
        return flow {
            try {
                emit(YifResult.Success(api.fetchBanner().data ?: listOf()))
            } catch (e: Exception) {
                emit(YifResult.Failure(e))
            }
        }
    }
}
