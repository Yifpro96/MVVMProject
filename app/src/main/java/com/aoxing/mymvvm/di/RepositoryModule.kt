package com.aoxing.mymvvm.di

import com.aoxing.mymvvm.ui.home.HomeRepository
import com.aoxing.mymvvm.ui.home.HomeViewModel
import com.aoxing.mymvvm.ui.square.SquareRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { HomeRepository(get()) }
    single { SquareRepository(get()) }
}