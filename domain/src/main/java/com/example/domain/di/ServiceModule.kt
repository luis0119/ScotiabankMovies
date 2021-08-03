package com.example.domain.di

import com.example.domain.repository.MovieRepository
import com.example.domain.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun providerMovieService(movieRepository: MovieRepository) : MovieService {
        return MovieService(movieRepository)
    }


}