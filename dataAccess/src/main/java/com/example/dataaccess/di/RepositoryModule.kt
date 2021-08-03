package com.example.dataaccess.di

import com.example.dataaccess.db.AppDatabase
import com.example.dataaccess.network.MovieService
import com.example.dataaccess.repository.MovieRepositoryImpl
import com.example.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providerMovieRepository(leagueServices: MovieService,
                                appDatabase: AppDatabase
    ) : MovieRepository {
        return MovieRepositoryImpl(appDatabase,leagueServices)
    }

}