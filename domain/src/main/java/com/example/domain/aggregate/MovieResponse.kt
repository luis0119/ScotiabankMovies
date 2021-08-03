package com.example.domain.aggregate

import com.example.domain.entity.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    var page : Int,
    @SerializedName("results")  var movies : List<Movie>,
)