package com.example.assignment1_singh.repository

import com.example.assignment1_singh.model.MovieSearchResponse
import com.example.assignment1_singh.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("/")
    fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") searchTerm: String
    ): Call<MovieSearchResponse>
    @GET("/")
    fun getMovieDetails(
        @Query("apikey") apiKey: String,
        @Query("i") imdbID: String
    ): Call<Movie>

}