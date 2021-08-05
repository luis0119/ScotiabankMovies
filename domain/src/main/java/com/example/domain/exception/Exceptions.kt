package com.example.domain.exception


import com.example.utilities.Constants.NOT_FOUND_MOVIES_MESSAGE

class MoviesNotFound : RuntimeException(NOT_FOUND_MOVIES_MESSAGE)
