package com.example.words.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.words.room.Word
import com.example.words.room.WordDao
import com.example.words.room.WordRoomDatabase

import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class WordRepositoryTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    lateinit var repository: WordRepository
    lateinit var wordDao: WordDao
    lateinit var wordDatabase: WordRoomDatabase

    var word1 =  Word("Test")

    @Before
    fun setup() {
        wordDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            WordRoomDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        wordDao = mock(WordDao::class.java)

        repository = WordRepository(wordDao)
    }

    @After
    fun closeDb() {
        wordDatabase.close()
    }

    @Test
    fun getAlphabetizedWords() {
        verify(wordDao).getAlphabetizedWords()
    }

    @Test
    fun insert() = runBlocking {
        repository.insert(word1)

        verify(wordDao).insert(word1)
    }

    @Test
    fun deleteAll() = runBlocking {
        repository.deleteAll()

        verify(wordDao).deleteAll()
    }

    @Test
    fun deleteWord() = runBlocking {
        repository.deleteWord(word1)

        verify(wordDao).deleteWord(word1)
    }
}