package com.example.words.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.words.data.IWordRepository
import com.example.words.data.WordRepository
import com.example.words.room.Word
import com.example.words.room.WordDao
import com.example.words.room.WordRoomDatabase
import com.nhaarman.mockitokotlin2.firstValue
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MainViewModelTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var wordDatabase: WordRoomDatabase
    private lateinit var wordDao: WordDao
    private lateinit var repository: IWordRepository
    private lateinit var viewModel: MainViewModel
    lateinit var wordListObserver: Observer<List<Word>>

    // How to mock out the mainlooper

    @Before
    fun setup() {
        wordDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            WordRoomDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        wordDao = wordDatabase.wordDao()

        repository = WordRepository(wordDao)
        viewModel = MainViewModel(repository, Dispatchers.Main)

        wordListObserver = mock()



    }

    @After
    fun closeDb() {
        wordDatabase.close()
    }

    @Test
    fun getAlphabetizedWords() = runBlocking {
        viewModel.wordList.observeForever(wordListObserver)
        verify(wordListObserver).onChanged(emptyList())
    }


    // TODO the something wrong with insert here, works fine when the app is running

    @Test
    fun insertWord() = runBlocking {
        val word1 = Word("Word1")


        // The error is here,
        // Tried using a Dispatcher.IO
        // ViewModel inside runBlocking doesn't work
        // TestCoroutineDispatcher only available in unit tests
        // What about ViewModelScope
        viewModel.insertWord(word1)

        viewModel.wordList.observeForever(wordListObserver)

        val listClass = ArrayList::class.java as Class<ArrayList<Word>>
        val argumentCaptor = ArgumentCaptor.forClass(listClass)

        verify(wordListObserver).onChanged(argumentCaptor.capture())

        assertTrue(argumentCaptor.value.size == 1)
    }

    @Test
    fun deleteWord() = runBlocking {
        val word1 = Word("Word1")
        val word2 = Word("Word2")
        viewModel.insertWord(word1)
        viewModel.insertWord(word2)

        viewModel.deleteWord(word1)

        viewModel.wordList.observeForever(wordListObserver)

        val listClass = ArrayList::class.java as Class<ArrayList<Word>>
        val argumentCaptor = ArgumentCaptor.forClass(listClass)

        verify(wordListObserver).onChanged(argumentCaptor.capture())


        assertTrue(argumentCaptor.value.size == 1)
        assertTrue(argumentCaptor.firstValue.contains(word2))


    }
}