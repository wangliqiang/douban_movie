package com.app.douban_movie.di

import com.app.douban_movie.BuildConfig
import com.app.douban_movie.data.remote.RequestInterceptor
import com.app.douban_movie.data.remote.service.MovieService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

val networkModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.e(message)
            }
        })
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }

        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BuildConfig.MOVIE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()
    }

    single { get<Retrofit>().create(MovieService::class.java) }

}