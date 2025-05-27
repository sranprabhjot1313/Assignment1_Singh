package com.example.assignment1_singh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.assignment1_singh.model.Movie
import com.example.assignment1_singh.repository.MovieRepository

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository()
    val movies: LiveData<List<Movie>> = repository.movies

    fun searchMovies(apiKey: String, query: String) {
        repository.searchMovies(apiKey, query)
    }
}