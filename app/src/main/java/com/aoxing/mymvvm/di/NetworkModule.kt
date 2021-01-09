package com.aoxing.mymvvm.di

import com.aoxing.mymvvm.network.ApiService
import com.aoxing.mymvvm.network.LoggingInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level

val networkModule = module {
    single {
        val interceptor = LoggingInterceptor("okhttp").apply {
            setPrintLevel(LoggingInterceptor.Level.BODY)
            setColorLevel(Level.INFO)
        }
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(ApiService.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }
}