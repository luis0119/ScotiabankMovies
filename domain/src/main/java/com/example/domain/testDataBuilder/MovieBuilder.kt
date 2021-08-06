package com.example.domain.testDataBuilder

import com.example.domain.entity.Movie

data class MovieBuilder(
    var adult : Boolean = false,
    var backdrop_path : String = "backdrop_path",
    var genre_ids : List<Int> = listOf(0,1,2),
    var id : Int = 1,
    var original_language : String = "ES",
    var original_title : String = "Title",
    var overview : String = "overview",
    var popularity : Double = 0.0,
    var poster_path : String = "poster_path",
    var release_date : String = "release_date",
    var title : String = "Title",
    var video : Boolean = false,
    var vote_average : Double =  10.0,
    var vote_count : Int = 1000
)   {

    fun build(): Movie{
        return Movie(adult,backdrop_path,genre_ids,id,original_language,
            original_title, overview,popularity,poster_path,release_date,
            title,video,vote_average, vote_count)
    }

}