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
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class MainViewModelUnitTestTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

        lateinit var viewModel: MainViewModel
        lateinit var repository: FakeWordRepository

        lateinit var word1: Word

        val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

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
        fun wordList() {
            // Given

            // When action
            viewModel.wordList

            // action
            Mockito.verify(repository).allWords
        }

        @Test
        fun insertWord() = runBlocking {

            // When action, input or output
            viewModel.insertWord(word1)

            Mockito.verify(repository).insert(word1)
        }

        @Test
        fun deleteWord() = runBlocking {
            // Given, setup objects

            // When action
            viewModel.deleteWord(word1)

            // Then results
            Mockito.verify(repository).deleteWord(word1)
        }

        @Test
        fun deleteAllWords() = runBlocking {
            // Given, setup objects

            // When action
            viewModel.deleteAllWords()

            // Then results
            Mockito.verify(repository).deleteAll()
        }
}