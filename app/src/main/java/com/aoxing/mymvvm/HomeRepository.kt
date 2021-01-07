package com.aoxing.mymvvm

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.aoxing.mymvvm.model.HomeArticle
import com.aoxing.mymvvm.network.ApiService
import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.Flow

class HomeRepository(val api: ApiService) {

    private val mPageConfig = PagingConfig(
        pageSize = 10, //每页条目数量
        enablePlaceholders = true, //条目占位
        prefetchDistance = 3, //预加载距离
        initialLoadSize = 10 //初始化条目数量
    )

    fun fetchHomeArticle(): Flow<PagingData<HomeArticle.Data>> {
        Logger.e("fetchHomeArticle....")
        return Pager(
            config = mPageConfig
        ) {
            object : PagingSource<Int, HomeArticle.Data>() {
                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeArticle.Data> {
                    Logger.e("load data....")
                    val page = params.key ?: 0
                    return try {
                        with(api.fetchHomeArticleList(page).data!!) {
                            LoadResult.Page(
                                data = datas,
                                prevKey = if (page == 0) null else curPage - 1,
                                nextKey = if (curPage  == pageCount) null else curPage + 1
                            )
                        }
                    } catch (e: Exception) {
                        LoadResult.Error(e)
                    }
                }
            }
        }.flow
    }
}