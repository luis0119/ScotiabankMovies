package com.example.application.di

import com.example.application.integration.MovieFactory
import com.example.application.useCase.GetMoviesUseCase
import com.example.application.useCase.SaveMoviesUseCase
import com.example.domain.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun providerGetMoviesUseCase(movieFactory: MovieFactory,movieService: MovieService) : GetMoviesUseCase{
        return GetMoviesUseCase(movieFactory,movieService,Dispatchers.IO)
    }

    @Provides
    fun providerSaveMoviesUseCase(movieFactory: MovieFactory,movieService: MovieService) : SaveMoviesUseCase{
        return SaveMoviesUseCase(movieFactory,movieService,Dispatchers.IO)
    }

    @Provides
    fun provideVehicleFactory() : HashMap<Int, String> {
        return HashMap()
    }


}