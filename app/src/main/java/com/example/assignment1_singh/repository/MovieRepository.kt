package com.example.assignment1_singh.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignment1_singh.model.Movie
import com.example.assignment1_singh.model.MovieSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    fun searchMovies(apiKey: String, query: String) {
        val call = RetrofitClient.apiService.searchMovies(apiKey, query)
        call.enqueue(object : Callback<MovieSearchResponse> {
            override fun onResponse(
                call: Call<MovieSearchResponse>,
                response: Response<MovieSearchResponse>
            ) {
                if (response.isSuccessful && response.body()?.Search != null) {
                    _movies.postValue(response.body()!!.Search!!)
                }
            }

            override fun onFailure(call: Call<MovieSearchResponse>, t: Throwable) {
                _movies.postValue(emptyList())
            }
        })
    }
}