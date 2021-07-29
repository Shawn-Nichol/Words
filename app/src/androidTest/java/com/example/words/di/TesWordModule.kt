package com.example.words.di

import com.example.words.data.room.WordRepositoryFake
import com.example.words.data.IWordRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class TesWordModule {
    @Binds
    @Singleton
    abstract fun provideRepository(repository: WordRepositoryFake): IWordRepository
}