package com.example.words.di

import com.example.words.data.IMySharedPreferences
import com.example.words.data.IWordRepository
import com.example.words.data.MySharedPreferencesFake
import com.example.words.data.room.WordRepositoryFake
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
abstract class TestMySharedPreferenceModule {
    @Binds
    @Singleton
    abstract fun provideTestMySharedPreferenceFake(repository: MySharedPreferencesFake): IMySharedPreferences
}

