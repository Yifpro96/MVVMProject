package com.aoxing.mymvvm

import retrofit2.http.GET

interface ApiService {
    companion object{
        const val baseUrl = ""
    }

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Result
}