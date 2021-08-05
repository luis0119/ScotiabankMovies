package com.example.dataaccess.di

import com.example.dataaccess.network.MovieService
import com.example.utilities.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideService(): Retrofit {

        val okHttpClient =  OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        val interceptor = HttpLoggingInterceptor();
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY };
        okHttpClient.addInterceptor(interceptor).build();

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build();
    }

    @Provides
    fun providerLeaguesApi(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

}