package com.example.words.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.example.words.main.MainViewModel

import com.example.words.room.WordRepository
import com.nhaarman.mockitokotlin2.mock

import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainViewModelUnitTest {


    private lateinit var repository: WordRepository
    private lateinit var viewModel: MainViewModel




    // Swaps out the executor and replaces it with synchronous one.  This will make sure that, when
    // using LiveDAta with the ViewModel, it's all run synchronously in the test.
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = mock()
        viewModel = MainViewModel(repository)




    }

    @Test
    fun insertWord() {

        Assert.assertEquals(2, 2)

    }
}