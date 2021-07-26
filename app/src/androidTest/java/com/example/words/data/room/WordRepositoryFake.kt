package com.example.words.data.room

import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.words.data.IWordRepository
import com.example.words.data.room.Word
import com.example.words.data.room.WordDao
import com.example.words.data.room.WordRoomDatabase
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

open class WordRepositoryFake @Inject constructor() : IWordRepository {

    lateinit var database: WordRoomDatabase
    lateinit var wordDao: WordDao


    init {
        opendb()
        wordDao = database.wordDao()

        runBlocking {
            dummyData()
        }
    }

    override val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    override suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    override suspend fun deleteWord(word: Word) {
        wordDao.deleteWord(word)
    }

    override suspend fun deleteAll() {
        wordDao.deleteAll()
    }

    override suspend fun restore() {
        dummyData()
    }

    suspend fun dummyData() {
        for (i in 0..50) {
            val word = if (i < 10) {
                Word("Test word: 0$i")
            } else {
                Word("Test word: $i")
            }
            wordDao.insert(word)
        }
    }

    fun opendb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WordRoomDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }
}