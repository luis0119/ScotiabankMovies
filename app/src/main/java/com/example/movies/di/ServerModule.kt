package com.example.movies.di

import com.example.dataaccess.di.DataAccessComponent
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(subcomponents = [DataAccessComponent::class])
class ServerModule