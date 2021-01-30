package com.aoxing.mymvvm.ui.home

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.aoxing.mymvvm.common.Default_Page_Index
import com.aoxing.mymvvm.common.pageConfig
import com.aoxing.mymvvm.model.HomeArticle
import com.aoxing.mymvvm.model.YifResult
import com.aoxing.mymvvm.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class HomeRepository(val api: ApiService) {

    fun fetchHomeData(): Flow<PagingData<HomeArticle.Data>> {
        return Pager(config = pageConfig) { HomeArticlePagingSource(api) }.flow
    }

    fun fetchArticles(): Flow<PagingData<HomeArticle.Data>> {
        return Pager(config = pageConfig) { HomeArticlePagingSource(api) }.flow
    }

    fun fetchTopArticle(): Flow<YifResult<List<HomeArticle.Data>>> {
        return flow {
            try {
                emit(YifResult.Success(api.fetchTopArticle().data ?: listOf()))
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

class HomeArticlePagingSource(val api: ApiService) : PagingSource<Int, HomeArticle.Data>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeArticle.Data> {
        return try {
            val page = params.key ?: Default_Page_Index
            if (page == Default_Page_Index) {
                return coroutineScope {
                    val p1 = withContext(Dispatchers.Default) {
                        api.fetchTopArticle().data ?: listOf()
                    }.apply {
                        forEach { it.top = 1 }
                    }
                    val p2 = withContext(Dispatchers.Default) {
                        api.fetchArticles(page).data ?: HomeArticle()
                    }
                    LoadResult.Page(
                        data = p1 + p2.datas,
                        prevKey = if (page == Default_Page_Index) null else page - 1,
                        nextKey = if (p2.curPage == p2.pageCount) null else p2.curPage
                    )
                }
            } else {
                with(api.fetchArticles(page).data ?: HomeArticle()) {
                    LoadResult.Page(
                        data = datas,
                        prevKey = if (page == Default_Page_Index) null else page - 1,
                        nextKey = if (curPage == pageCount) null else curPage
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}