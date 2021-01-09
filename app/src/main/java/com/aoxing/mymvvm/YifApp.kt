package com.aoxing.mymvvm

import android.app.Application
import android.content.Context
import com.aoxing.mymvvm.di.networkModule
import com.aoxing.mymvvm.di.repositoryModule
import com.aoxing.mymvvm.di.viewModelModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.properties.Delegates

class YifApp : Application() {

    companion object {
        var context: Context by Delegates.notNull()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        startKoin {
            androidContext(this@YifApp)
            modules(listOf(networkModule, viewModelModule, repositoryModule))
        }
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
            .methodCount(0) // (Optional) How many method line to show. Default 2
            .methodOffset(7) // (Optional) Hides internal method calls up to offset. Default 5
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }
}