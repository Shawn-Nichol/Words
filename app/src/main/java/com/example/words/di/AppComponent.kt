package com.example.words.di

import android.content.Context
import com.example.words.main.MainActivity
import com.example.words.main.WordListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Class that can be injected
    fun inject(activity: MainActivity)
    fun inject(fragment: WordListFragment)
}