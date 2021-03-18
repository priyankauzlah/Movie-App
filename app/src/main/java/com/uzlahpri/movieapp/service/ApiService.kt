package com.uzlahpri.movieapp.service

import com.uzlahpri.movieapp.model.ResponseMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie")
    fun getMoviesData(
        @Query("language") lang: String?,
        @Query("apiKey") apiKey: String?,
    ): Call<ResponseMovies>
}
