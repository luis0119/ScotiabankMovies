package com.example.movies

import android.app.Application
import com.example.movies.di.AppComponent
import com.example.movies.di.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }


    override fun onCreate() {
        super.onCreate()
        component.inject(this)

    }

}