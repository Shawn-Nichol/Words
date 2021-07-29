package com.example.words.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    TestMySharedPreferenceModule::class,
    RoomModule::class,
    TesWordModule::class,
    DispatchersModule::class
])
interface TestAppComponent : AppComponent{
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): TestAppComponent
    }
}