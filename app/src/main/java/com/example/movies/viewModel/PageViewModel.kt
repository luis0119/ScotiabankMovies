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
    private var moviesList = ArrayList<Movie>()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies get() = _movies
    private val _message = MutableLiveData<String>()
    val message : LiveData<String> get() = _message
    private val _progress =  MutableLiveData<Boolean>()
    val progress : LiveData<Boolean> get() = _progress

    fun getMovies(position: Int) {
        val movieRequest = MovieRequest(page, position)

        viewModelScope.launch {
            _progress.value = true

            try {
                val result = getMoviesUseCase.execute(movieRequest)
                moviesList.addAll(result.movies)
                saveMoviesUseCase.execute(movieRequest,result)
                movies.value = moviesList
                page++
            }catch (ex : Exception){
                _message.value = ex.message!!
            }finally {
                _progress.value = false
            }

        }
    }

}