package com.aoxing.mymvvm.di

import com.aoxing.mymvvm.ui.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { HomeViewModel(get()) }
}