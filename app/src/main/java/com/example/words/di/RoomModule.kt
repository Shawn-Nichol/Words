package com.example.words.di

import android.content.Context
import com.example.words.data.IWordRepository
import com.example.words.data.WordRepository
import com.example.words.room.WordDao
import com.example.words.room.WordRoomDatabase
import com.example.words.room.insertDBWords
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
class RoomModule {
    companion object {
        val applicationScope = CoroutineScope(SupervisorJob())

        @Provides
        @Singleton
        fun provideDatabase(context: Context): WordRoomDatabase {
            return WordRoomDatabase.getDatabase(context, applicationScope)
        }

        @Provides
        @Singleton
        fun provideDao(database: WordRoomDatabase): WordDao {
            return database.wordDao()
        }


    }


}