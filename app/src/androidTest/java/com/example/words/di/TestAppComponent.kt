package com.example.words.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules =[])
interface TestAppComponent : AppComponent {

    // Factory to create instances of the AppComponent.
    @Component.Factory
    interface Factory {
        // BindInstance: The context passed in will be available to the test graph.
        fun create(@BindsInstance context: Context): TestAppComponent
    }
}