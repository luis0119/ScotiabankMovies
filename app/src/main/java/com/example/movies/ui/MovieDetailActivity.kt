package com.example.movies.ui

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.domain.entity.Movie
import com.example.movies.R
import com.example.movies.databinding.ActivityMovieDetailBinding
import com.example.utilities.Constants.ARG_MOVIE
import com.example.utilities.Images
import com.example.utilities.RequestImage

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityMovieDetailBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadToolbar()
        loadView(intent.getSerializableExtra(ARG_MOVIE) as? Movie)
    }

    private fun loadView(movie: Movie?) {
        movie?.let {
            with(binding){
                val requestImage = RequestImage(progress,logo,movie.poster_path,applicationContext, 750, 500)
                Images.loadImageURL(requestImage)
                rating.text = movie.vote_average.toString()
                nameMovie.text = movie.title
                description.text = movie.overview
                date.text = movie.release_date
            }
        }
    }

    private fun loadToolbar(){
        with(binding.toolbar){
            setSupportActionBar(this)
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener { onBackPressed() }
        }
    }
}