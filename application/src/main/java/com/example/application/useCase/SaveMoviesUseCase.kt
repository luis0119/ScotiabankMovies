package com.example.application.useCase

import com.example.application.exception.CategoryNotFound
import com.example.application.integration.MovieFactory
import com.example.domain.aggregate.MovieRequest
import com.example.domain.aggregate.MovieResponse
import com.example.domain.service.MovieService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveMoviesUseCase @Inject constructor (
    private val movieFactory: MovieFactory,
    private val movieService: MovieService,
    private val dispatcher : CoroutineDispatcher
) {

    suspend fun execute(movieRequest: MovieRequest,movieResponse: MovieResponse) {
        movieRequest.category = movieFactory.getTypeMovie(movieRequest.position).
        orElseThrow {
            throw CategoryNotFound()
        }
        withContext(dispatcher) {
            movieService.saveMovies(movieRequest,movieResponse)
        }

    }

}