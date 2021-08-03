package com.example.dataaccess.repository

import com.example.dataaccess.anticorruption.MovieTranslator
import com.example.dataaccess.db.AppDatabase
import com.example.dataaccess.network.MovieService
import com.example.domain.aggregate.MovieRequest
import com.example.domain.aggregate.MovieResponse
import com.example.domain.repository.MovieRepository
import com.example.utilities.Constants.API_KEY
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val movieService: MovieService
    ) : MovieRepository {

    override suspend fun getMovies(request: MovieRequest): Response<MovieResponse> {
        return movieService.getMovies(request.category,API_KEY,request.page)
    }

    override suspend fun saveMovies(category: String,movieResponse: MovieResponse) {
        val movies = MovieTranslator.fromDomainToMoviesDb(movieResponse,category)
        database.movieDao().insertMovies(movies)
    }

    override suspend fun getMoviesDB(request: MovieRequest): MovieResponse {
        val moviesDB = database.movieDao().getMovies(request.page,request.category)
        val movies = MovieTranslator.convertMoviesDBToMovies(moviesDB)
        return MovieResponse(request.page,movies)
    }
}