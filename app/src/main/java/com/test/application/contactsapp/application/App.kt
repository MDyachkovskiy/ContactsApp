package com.test.application.contactsapp.application

import android.app.Application
import com.test.application.contactsapp.di.databaseModule
import com.test.application.contactsapp.di.networkModule
import com.test.application.contactsapp.di.repositoryModule
import com.test.application.contactsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, databaseModule, repositoryModule, viewModelModule))
        }
    }
}