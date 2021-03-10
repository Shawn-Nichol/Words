package com.example.words.di

import android.content.Context
import com.example.words.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Class that can be injected
    fun  inject(activity: MainActivity)
}