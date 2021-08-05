package com.example.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.R
import com.example.movies.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityMovieDetailBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMovieDetailBinding.inflate(layoutInflater)
    }

    private fun loadToolbar(){

    }
}