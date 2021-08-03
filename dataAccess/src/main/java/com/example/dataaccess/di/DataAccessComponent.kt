package com.example.dataaccess.di

import android.provider.ContactsContract
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [DatabaseModule::class,RepositoryModule::class,NetworkModule::class])
interface DataAccessComponent {


    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun bindData(data: ContactsContract.Contacts.Data): Builder
        fun build(): DataAccessComponent
    }
}