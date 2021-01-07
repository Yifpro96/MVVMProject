package com.aoxing.mymvvm.network

import com.aoxing.mymvvm.model.HomeArticle
import com.aoxing.mymvvm.model.YifResult
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object{
        const val baseUrl = ""
    }

    @GET("article/list/{page}/json")
    suspend fun fetchHomeArticleList(@Path("page") page: Int = 0): YifResult<HomeArticle>
}