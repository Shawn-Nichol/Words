package com.example.words.di

import android.content.Context
import com.example.words.main.MainActivity
import com.example.words.main.fragments.details.DetailsFragment
import com.example.words.main.fragments.newword.NewWordFragment
import com.example.words.main.fragments.wordlist.WordListFragment
import com.example.words.room.InsertDBWords
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class, StorageModule::class, DispatchersModule::class])
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
    fun inject(dao: InsertDBWords)
}