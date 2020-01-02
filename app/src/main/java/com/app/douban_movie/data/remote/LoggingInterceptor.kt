package com.app.douban_movie.data.remote

import com.app.douban_movie.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class LoggingInterceptor {

    companion object {
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.e(message)
            }
        }).apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.BASIC
            }
        }
    }

}