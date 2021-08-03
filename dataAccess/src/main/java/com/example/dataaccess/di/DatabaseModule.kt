package com.example.dataaccess.di

import android.content.Context
import com.example.dataaccess.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) : AppDatabase {
        return AppDatabase.getDatabase(appContext)
    }

}