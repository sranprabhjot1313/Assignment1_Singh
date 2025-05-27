package com.example.assignment1_singh.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.assignment1_singh.databinding.ActivityMovieDetailsBinding
import com.example.assignment1_singh.model.Movie
import com.example.assignment1_singh.repository.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imdbID = intent.getStringExtra("imdbID") ?: return
        val apiKey = "49ad26ef"

        RetrofitClient.apiService.getMovieDetails(apiKey, imdbID)
            .enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    val movie = response.body()
                    if (movie != null) {
                        binding.titleText.text = movie.Title
                        binding.yearText.text = "Year: ${movie.Year}"
                        binding.posterImage.load(movie.Poster)
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    binding.titleText.text = "Failed to load details"
                }
            })
        binding.backButton.setOnClickListener {
            finish()  // Goes back to MainActivity
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
