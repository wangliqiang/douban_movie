package com.app.douban_movie.di

import com.app.douban_movie.data.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MovieRepository(get()) }
}