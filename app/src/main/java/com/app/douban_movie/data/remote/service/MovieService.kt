package com.app.douban_movie.data.remote.service

import com.app.douban_movie.data.model.Theaters
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("in_theaters")
    suspend fun getInTheaters(@Query("city") city: String, @Query("start") start: Int, @Query("count") count: Int): Theaters


    @GET("coming_soon")
    suspend fun getComingSoon(@Query("city") city: String, @Query("start") start: Int, @Query("count") count: Int): Theaters
}