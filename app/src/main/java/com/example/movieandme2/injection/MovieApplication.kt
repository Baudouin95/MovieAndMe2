package com.example.movieandme2.injection

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApplication : Application(){
    override fun onCreate(){
        super.onCreate()
        startKoin {
            androidContext(this@MovieApplication)
            modules(presentaionModule, domainModule, dataModule)
        }
    }
}