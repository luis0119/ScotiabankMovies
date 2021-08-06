package com.example.domain.testDataBuilder

import com.example.domain.aggregate.MovieResponse
import com.example.domain.entity.Movie

 data class MovieResponseBuilder(val page: Int = 1, val movies : List<Movie> = ArrayList()) {

    fun build() : MovieResponse {
        return MovieResponse(page,movies)
    }

}