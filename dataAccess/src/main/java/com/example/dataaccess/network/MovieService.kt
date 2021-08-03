package com.example.dataaccess.network

import com.example.domain.aggregate.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/{type}")
    suspend fun getMovies(
        @Path("type") typeMovie: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieResponse>


}