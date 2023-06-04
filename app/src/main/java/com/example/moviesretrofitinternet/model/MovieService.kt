package com.example.moviesretrofitinternet.model


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    fun listPopularMovies(@Query("api_key") apiKey: String): Call<MovieResult>
}