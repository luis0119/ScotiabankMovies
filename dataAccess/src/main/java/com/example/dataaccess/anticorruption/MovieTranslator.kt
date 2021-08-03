package com.example.dataaccess.anticorruption

import com.example.dataaccess.db.MovieDB
import com.example.domain.aggregate.MovieResponse
import com.example.domain.entity.Movie

class MovieTranslator {


    companion object {

        fun fromDomainToMoviesDb(movieResponse: MovieResponse,type: String) : List<MovieDB>{
            val movies = ArrayList<MovieDB>()
            movieResponse.movies.map {
                val movieDB = MovieDB()
                with(movieDB){
                    adult = it.adult
                    backdropPath = it.backdrop_path ?: ""
                    id = it.id
                    originalLanguage = it.original_language
                    originalTitle = it.original_title
                    overview = it.overview
                    category = type
                    popularity = it.popularity
                    posterPath = it.poster_path
                    page = movieResponse.page
                    releaseDate = it.release_date
                    title = it.title
                    video = it.video
                    voteAverage = it.vote_average
                    voteCount = it.vote_count
                }
                movies.add(movieDB)
            }
            return movies
        }

        fun convertMoviesDBToMovies(moviesDB: List<MovieDB>) : List<Movie>{
            val movies = ArrayList<Movie>()
            moviesDB.map {
                with(it){
                    val movie = Movie(adult,backdropPath,ArrayList(),id,originalLanguage,originalTitle,overview,popularity,posterPath,releaseDate,title,video,voteAverage,voteCount)
                    movies.add(movie)
                }

            }
            return movies
        }



    }

}