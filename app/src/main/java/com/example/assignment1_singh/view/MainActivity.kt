package com.example.assignment1_singh.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment1_singh.adapter.MovieAdapter
import com.example.assignment1_singh.databinding.ActivityMainBinding
import com.example.assignment1_singh.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieViewModel by viewModels()
    private val apiKey = "49ad26ef"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.searchButton.setOnClickListener {
            val query = binding.searchEditText.text.toString()
            if (query.isNotEmpty()) {
                viewModel.searchMovies(apiKey, query)
            }
        }

        viewModel.movies.observe(this) { movieList ->
            binding.recyclerView.adapter = MovieAdapter(movieList) { movie ->
                val intent = Intent(this, MovieDetailsActivity::class.java)
                intent.putExtra("imdbID", movie.imdbID)
                startActivity(intent)
            }
        }
    }
}
