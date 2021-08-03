package com.example.application.useCase

import com.example.application.exception.CategoryNotFound
import com.example.application.integration.MovieFactory
import com.example.domain.aggregate.MovieRequest
import com.example.domain.aggregate.MovieResponse
import com.example.domain.service.MovieService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor (
    private val movieFactory: MovieFactory,
    private val movieService: MovieService,
    private val dispatcher : CoroutineDispatcher
    ) : Iterator<MovieResponse,MovieRequest> {

    override suspend fun execute(params: MovieRequest): MovieResponse {
        params.category = movieFactory.getTypeMovie(params.position).
        orElseThrow {
            throw CategoryNotFound()
        }
        return withContext(dispatcher) {
            movieService.getMovies(params)
        }

    }


}