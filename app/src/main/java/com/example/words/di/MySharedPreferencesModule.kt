package com.example.words.di

import com.example.words.data.IMySharedPreferences
import com.example.words.data.MySharedPreferences
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class MySharedPreferencesModule {

    @Binds
    @Singleton
    abstract fun provideSharedPreferences(repository: MySharedPreferences): IMySharedPreferences
}




