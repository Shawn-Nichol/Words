package com.example.word.di

import android.content.Context
import com.example.words.di.AppComponent
import com.example.words.di.DispatchersModule
import com.example.words.di.RoomModule
import com.example.words.di.StorageModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [RoomModule::class, StorageModule::class, DispatchersModule::class])
interface TestAppComponent : AppComponent{
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): TestAppComponent
    }
}