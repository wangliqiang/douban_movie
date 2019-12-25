package com.app.douban_movie.di

import com.app.douban_movie.ui.viewmodels.HotViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HotViewModel(get()) }
}