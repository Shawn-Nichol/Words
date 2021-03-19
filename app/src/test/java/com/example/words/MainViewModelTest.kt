package com.example.words


import com.example.words.data.FakeWordRepository

import com.example.words.main.MainViewModel
import com.example.words.room.Word
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi

class MainViewModelTest {


    lateinit var viewModel: MainViewModel
    lateinit var repository: FakeWordRepository

    @Before
    fun setup() {
        repository = mock()
        viewModel = MainViewModel(repository)

    }

    @Test
    fun wordList() {
        // Given

        // When action
        viewModel.wordList

        // action
        verify(repository).allWords
    }

    @Test
    fun insertWord() = runBlocking {
        // Given Setup the objects
        val word1 = Word("Word1")

        // When action, input or output
        viewModel.insertWord(word1)

        verify(repository).insert(word1)
    }

    @Test
    fun deleteWord() = runBlocking{
        // Given, setup objects
        val word1 = Word("Word1")
        // When action
        viewModel.deleteWord(word1)

        // Then results
        verify(repository).deleteWord(word1)
    }

    @Test
    fun deleteAllWords() = runBlocking {
        // Given, setup objects

        // When action
        viewModel.deleteAllWords()

        // Then results
        verify(repository).deleteAll()
    }

}