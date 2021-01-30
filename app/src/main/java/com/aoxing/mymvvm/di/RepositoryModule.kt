package com.aoxing.mymvvm.di

import com.aoxing.mymvvm.ui.home.HomeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { HomeRepository(get()) }
}