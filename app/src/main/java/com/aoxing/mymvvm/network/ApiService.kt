package com.aoxing.mymvvm.network

import com.aoxing.mymvvm.model.HomeArticle
import com.aoxing.mymvvm.model.HttpResp
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object{
        const val baseUrl = "https://www.wanandroid.com/"
    }

    @GET("user_article/list/{page}/json")
    suspend fun fetchSquares(@Path("page") page: Int = 0): HttpResp<HomeArticle>

    @GET("article/list/{page}/json")
    suspend fun fetchArticles(@Path("page") page: Int = 0): HttpResp<HomeArticle>

    @GET("article/top/json")
    suspend fun fetchTopArticle(): HttpResp<List<HomeArticle.Data>>

    @GET("/banner/json")
    suspend fun fetchBanner(): HttpResp<List<HomeArticle.Data>>
}