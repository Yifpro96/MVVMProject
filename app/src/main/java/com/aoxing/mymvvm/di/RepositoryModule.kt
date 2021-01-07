package com.aoxing.mymvvm.di

import com.aoxing.mymvvm.HomeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { HomeRepository(get()) }
}