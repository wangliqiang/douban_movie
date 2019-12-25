package com.app.douban_movie

import android.app.Application
import com.app.douban_movie.di.networkModule
import com.app.douban_movie.di.repositoryModule
import com.app.douban_movie.di.viewModelModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        startKoin {
            androidContext(this@App)
            modules(networkModule)
            modules(viewModelModule)
            modules(repositoryModule)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}