package com.example.words

import android.app.Application
import com.example.words.di.AppComponent
import com.example.words.di.DaggerAppComponent

/**
 * MyApplication creates a Dagger graph. this allows the graph to be in memory as long as the app is
 * running. This way the graph is attached to the lifecycle and includes application context in the
 * graph.
 */
open class MyApplication : Application() {
    // Instance of the AppComponent will be used by all components in the project.
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    /**
     *  initializeComponent
     *  Passes the application context to the Dagger graph.
     */
    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}