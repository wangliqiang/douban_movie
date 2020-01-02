package com.app.douban_movie.di

import com.app.douban_movie.BuildConfig
import com.app.douban_movie.data.remote.LoggingInterceptor
import com.app.douban_movie.data.remote.RequestInterceptor
import com.app.douban_movie.data.remote.service.MovieService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .addInterceptor(LoggingInterceptor.loggingInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BuildConfig.MOVIE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(MovieService::class.java) }

}