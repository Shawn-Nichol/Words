package com.example.words

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.words.main.MainViewModel
import com.example.words.room.Word
import com.example.words.room.WordDao
import com.example.words.data.WordRepository
import com.example.words.room.WordRoomDatabase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: MainViewModel
    lateinit var repository: WordRepository
    lateinit var wordDatabase: WordRoomDatabase
    lateinit var wordDao: WordDao

    var word1 = Word("Word1")


    @Before
    fun setup() {
        wordDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            WordRoomDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        wordDao = wordDatabase.wordDao()
        repository = mock()
        viewModel = MainViewModel(repository)
    }

    @After
    fun closeDb() {
        wordDatabase.close()
    }

    @Test
    fun getAlphabetizedWords() = runBlocking{
        verify(repository.allWords)
    }

}