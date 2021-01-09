package com.aoxing.mymvvm

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.aoxing.mymvvm.model.HomeArticle
import com.aoxing.mymvvm.network.ApiService
import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.Flow

class HomeRepository(private val api: ApiService) {

    fun fetchHomeArticle(): Flow<PagingData<HomeArticle.Data>> {
        return Pager(config = pageConfig) { HomeArticlePagingSource(api) }.flow
    }
}

class HomeArticlePagingSource(val api: ApiService) : PagingSource<Int, HomeArticle.Data>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeArticle.Data> {
        return try {
            val page = params.key ?: Default_Page_Index
            with(api.fetchHomeArticleList(page).data!!) {
                LoadResult.Page(
                    data = datas,
                    prevKey = if (page == Default_Page_Index) null else page - 1,
                    nextKey = if (curPage == pageCount) null else curPage
                )
            }
        } catch (e: Exception) {
            Logger.e("exception ${e.message}")
            LoadResult.Error(e)
        }
    }
}