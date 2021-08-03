package com.example.movies.viewModel

import androidx.lifecycle.*
import com.example.application.useCase.GetMoviesUseCase
import com.example.application.useCase.SaveMoviesUseCase
import com.example.domain.aggregate.MovieRequest
import com.example.domain.entity.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

class PageViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase
) : ViewModel() {


    private var page = 1
    private val _movies = MutableLiveData<List<Movie>>()
    val movies get() = _movies

    fun getMovies(position: Int) {
        val movieRequest = MovieRequest(page, position)

        viewModelScope.launch {
            val result = getMoviesUseCase.execute(movieRequest)
            saveMoviesUseCase.execute(movieRequest,result)
            _movies.value = result.movies
        }
    }

}