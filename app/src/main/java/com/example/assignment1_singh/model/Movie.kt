package com.example.assignment1_singh.model

data class Movie(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
)

data class MovieSearchResponse(
    val Search: List<Movie>?,
    val totalResults: String?,
    val Response: String,
    val Error: String? = null
)

