package com.aoxing.mymvvm.network

import com.aoxing.mymvvm.model.HomeArticle
import com.aoxing.mymvvm.model.TopArticle
import com.aoxing.mymvvm.model.YifResp
import com.aoxing.mymvvm.model.YifResult
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object{
        const val baseUrl = "https://www.wanandroid.com/"
    }

    @GET("article/top/json")
    suspend fun fetchTopArticle(): YifResp<HomeArticle.Data>

    @GET("article/list/{page}/json")
    suspend fun fetchArticles(@Path("page") page: Int = 0): YifResp<HomeArticle>
}