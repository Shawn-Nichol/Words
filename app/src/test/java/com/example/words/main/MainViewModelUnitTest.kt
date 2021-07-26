package com.example.words.main


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.filters.SmallTest
import com.example.words.data.WordRepository
import com.example.words.data.room.Word
import com.example.words.testutils.MainCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@SmallTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MainViewModelUnitTest {


    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    // Runs livedata on the main UI thread and waits for results.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    @Mock
    private lateinit var repository: WordRepository

    private lateinit var word1: Word

    // Passes a Test dispatcher into the ViewModel for testing coroutine scopes.
    private val testDispatcher: TestCoroutineDispatcher = mainCoroutineRule.dispatcher

    @Before
    fun setup() {

        MockitoAnnotations.openMocks(this)

        viewModel = MainViewModel(repository, testDispatcher)

        word1 = Word("Word1")
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `get word list from repository`() {
        // Given

        // When action
        viewModel.wordList

        // Then
        verify(repository).allWords
    }

    @Test
    fun `insert a word to the database`() = mainCoroutineRule.runBlockingTest {

        // When action, input or output
        viewModel.insertWord(word1)

        // Then
        verify(repository).insert(word1)
    }

    @Test
    fun `delete a word from the database`() = mainCoroutineRule.runBlockingTest {
        // Given, setup objects

        // When action
        viewModel.deleteWord(word1)

        // Then results
        verify(repository).deleteWord(word1)
    }

    @Test
    fun `delete all the  word from the database`() = mainCoroutineRule.runBlockingTest {
        // Given, setup objects

        // When action
        viewModel.deleteAllWords()

        // Then results
        verify(repository).deleteAll()
    }

    @Test
    fun `restore original words in database`() = mainCoroutineRule.runBlockingTest {
        viewModel.restoreList()

        verify(repository).restore()
    }
}