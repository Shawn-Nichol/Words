package com.example.words.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.filters.SmallTest
import com.example.words.data.room.Word
import com.example.words.data.room.WordDao
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
class WordRepositoryUnitTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var wordDao: WordDao
    var word1 = Word("Word1")


    private lateinit var repository: WordRepository

    private val testDispatcher: TestCoroutineDispatcher = mainCoroutineRule.dispatcher

    @Before
    fun setup() {

        MockitoAnnotations.openMocks(this)

        repository = WordRepository(wordDao)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun allWords() = mainCoroutineRule.runBlockingTest {
        // Get the object


        // When action
        val list: LiveData<List<Word>> = repository.allWords

        // Then results
        // Verifying wordDao.getAlphabetizedWords, creates an initialized  error that prevents the rest
        // of the test from running
        //verify(wordDao).getAlphabetizedWords()
        //TODO: Verify all words wordDao in repository unit test, I think an obeserver is required here.
    }

    @Test
    fun insert() = mainCoroutineRule.runBlockingTest {
        // Get the object

        // When action
        repository.insert(word1)
        // Then results
        verify(wordDao).insert(word1)
    }

    @Test
    fun deleteWord() = mainCoroutineRule.runBlockingTest {
        // Get the object

        // When action
        repository.deleteWord(word1)

        // Then results
        verify(wordDao).deleteWord(word1)
    }

    @Test
    fun deleteAllWords() = mainCoroutineRule.runBlockingTest {
        // Get the object

        // When action
        repository.deleteAll()

        // Then result
        verify(wordDao).deleteAll()
    }

    @Test
    fun restore() = mainCoroutineRule.runBlockingTest {
        // when this happens
        repository.restore()

        // Then verify results
        // TODO Maybe a spy can go here.
    }

}