package com.example.words.di

import android.content.Context
import com.example.words.main.MainActivity
import com.example.words.main.details.DetailsFragment
import com.example.words.main.newword.NewWordFragment
import com.example.words.main.wordlist.WordListFragment
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
    fun inject(fragment: NewWordFragment)
    fun inject(fragment: DetailsFragment)
}