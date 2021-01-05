package com.aoxing.mymvvm

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.aoxing.mymvvm.model.HomeArticle
import kotlinx.coroutines.flow.Flow

class HomeRepository {

    private val mPageConfig = PagingConfig(
        pageSize = 10, //每页条目数量
        enablePlaceholders = true, //条目占位
        prefetchDistance = 3, //预加载距离
        initialLoadSize = 10 //初始化条目数量
    )

    fun fetchHomeArticle(): Flow<PagingData<HomeArticle.Data>> {
        return Pager(
            config = mPageConfig
        ) {
            object : PagingSource<Int, HomeArticle.Data>() {
                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeArticle.Data> {
                }
            }
        }.flow
    }
}