package com.aoxing.mymvvm.di

import com.aoxing.mymvvm.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainViewModel(get()) }
}