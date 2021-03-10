package com.example.words

import android.app.Application
import com.example.words.di.AppComponent
import com.example.words.di.DaggerAppComponent

class MyApplication : Application() {
    // Instance of the AppComponent will be used by all components in the project.
    val appComponent: AppComponent by lazy {
        // Passes the application context to the Dagger graph.
        DaggerAppComponent.factory().create(applicationContext)
    }
}