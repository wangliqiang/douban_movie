package com.app.douban_movie.data

import com.app.douban_movie.data.model.Theaters
import com.app.douban_movie.data.remote.service.MovieService
import timber.log.Timber

class MovieRepository(private val movieService: MovieService){

    init {
        Timber.d("Injection MovieRepository")
    }

    suspend fun loadIntheaters(city: String, start: Int, count: Int): Theaters {
        return movieService.getInTheaters(city, start, count)
    }
}