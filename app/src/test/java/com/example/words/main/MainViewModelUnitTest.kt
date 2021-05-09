package com.example.words.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.words.doubles.FakeWordRepository
import com.example.words.room.Word
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class MainViewModelUnitTest {

    // Runs livedata on the main UI thread and waits for results.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    private lateinit var repository: FakeWordRepository

    private lateinit var word1: Word

    // Passes a Test dispatcher into the ViewModel for testing coroutine scopes.
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        repository = mock()
        viewModel = MainViewModel(repository, testDispatcher)

        Dispatchers.setMain(testDispatcher)

        word1 = Word("Word1")
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `get word list from repository`() {
        // Given

        // When action
        viewModel.wordList

        // Then
        Mockito.verify(repository).allWords
    }

    @Test
    fun `insert a word to the database`() = runBlocking {

        // When action, input or output
        viewModel.insertWord(word1)

        // Then
        Mockito.verify(repository).insert(word1)
    }

    @Test
    fun `delete a word from the database`() = runBlocking {
        // Given, setup objects

        // When action
        viewModel.deleteWord(word1)

        // Then results
        Mockito.verify(repository).deleteWord(word1)
    }

    @Test
    fun `delete all the  word from the database`() = runBlocking {
        // Given, setup objects

        // When action
        viewModel.deleteAllWords()

        // Then results
        Mockito.verify(repository).deleteAll()
    }
}