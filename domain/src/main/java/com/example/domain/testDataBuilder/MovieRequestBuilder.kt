package com.example.domain.testDataBuilder

import com.example.domain.aggregate.MovieRequest

data class MovieRequestBuilder(val page : Int = 1,
                               val position : Int = 1,
                               val type : String = "top_rated") {

    fun build() : MovieRequest {
        return MovieRequest(page,position).apply {
            category = type
        }
    }

}