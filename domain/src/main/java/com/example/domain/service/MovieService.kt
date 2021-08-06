package com.example.domain.service

import com.example.domain.aggregate.MovieRequest
import com.example.domain.aggregate.MovieResponse
import com.example.domain.exception.MoviesNotFound
import com.example.domain.repository.MovieRepository
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.Exception

class MovieService @Inject constructor(
    private val repository: MovieRepository,
) {


    suspend fun getMovies(movieRequest: MovieRequest): MovieResponse {

        return try {
            val response = repository.getMovies(movieRequest)

            if (response.isSuccessful) {
                validateMovies(response.body())
            } else {
                repository.getMoviesDB(movieRequest)
            }
        } catch (ex: UnknownHostException) {
            repository.getMoviesDB(movieRequest)
        }
    }

    suspend fun saveMovies(movieRequest: MovieRequest,movieResponse: MovieResponse){
        val data = repository.getMoviesDB(movieRequest)

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