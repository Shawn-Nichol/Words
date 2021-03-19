package com.example.words.di

import com.example.words.data.IWordRepository
import com.example.words.data.WordRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class StorageModule {
    @Binds
    @Singleton
    abstract fun provideRepository(repository: WordRepository): IWordRepository
}