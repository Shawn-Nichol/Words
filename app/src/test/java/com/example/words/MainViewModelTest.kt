package com.example.words

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.words.data.FakeWordRepository
import com.example.words.data.WordRepository
import com.example.words.main.MainViewModel
import com.example.words.room.Word
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi

class MainViewModelTest {


    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        val repository = FakeWordRepository()
        viewModel = MainViewModel(repository)
    }

    @Test
    fun testOne() {
        val word = Word("Word1")
    }

}