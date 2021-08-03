package com.example.movies.di

import com.example.movies.App
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ServerModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder
    {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)

}