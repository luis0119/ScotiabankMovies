package com.example.domain.service

import com.example.domain.aggregate.MovieRequest
import com.example.domain.aggregate.MovieResponse
import com.example.domain.exception.MoviesNotFound
import com.example.domain.repository.MovieRepository
import javax.inject.Inject

class MovieService @Inject constructor(
    private val repository: MovieRepository,
) {


    suspend fun getMovies(movieRequest: MovieRequest) : MovieResponse {
        val response = repository.getMovies(movieRequest)
        return if (response.isSuccessful){
            validateMovies(response.body())
        }else{
            repository.getMoviesDB(movieRequest)
        }
    }

    suspend fun saveMovies(movieRequest: MovieRequest,movieResponse: MovieResponse){
        val data =  repository.getMoviesDB(movieRequest)
        if (data.movies.isEmpty()) {
            repository.saveMovies(movieRequest.category,movieResponse)
        }

    }

    private fun validateMovies(result : MovieResponse?) : MovieResponse{
        if (result != null){
            return result
        }else {
            throw MoviesNotFound()
        }
    }

}