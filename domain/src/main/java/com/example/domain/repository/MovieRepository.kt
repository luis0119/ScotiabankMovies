package com.example.domain.repository

import com.example.domain.aggregate.MovieRequest
import com.example.domain.aggregate.MovieResponse
import retrofit2.Response


interface MovieRepository {

    suspend fun getMovies(request: MovieRequest) : Response<MovieResponse>

    suspend fun saveMovies(category: String, movieResponse: MovieResponse)

    suspend fun getMoviesDB(request: MovieRequest) : MovieResponse

}