package com.example.utilities

object Constants {

    //Url
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    const val THREE_SECONDS : Long = 3000

    //Position Tabs
    const val POSITION_TOP_RATED = 0
    const val POSITION_POPULAR = 1
    const val POSITION_UPCOMING = 2

    //Category
    const val TYPE_TOP_RATED = "top_rated"
    const val TYPE_POPULAR = "popular"
    const val TYPE_UPCOMING = "upcoming"

    //Exceptions
    const val NOT_FOUND_CATEGORY_MESSAGE = "No se encuentra esta categoria."
    const val NOT_FOUND_MOVIES_MESSAGE = "Por el momento no hay mas peliculas."
    const val ERROR_GENERAL_MESSAGE = "Ha ocurrido un error intentalo mas tarde."

    //App
    const val API_KEY = "7d8753df41853fd10adbaa9e1746c1d4"
    const val ARG_SECTION_NUMBER = "section_number"
    const val DIRECTION_RECYCLER = 1
    const val ARG_MOVIE = "movie"

    val TAB_TITLES = arrayOf(
        R.string.top_rated,
        R.string.popular,
        R.string.upcoming
    )

}