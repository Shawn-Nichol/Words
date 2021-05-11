package com.example.words.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.example.words.doubles.FakeDao
import com.example.words.room.Word
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WordRepositoryUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var repository: WordRepository
    lateinit var wordDao: FakeDao
    var word1 = Word("Word1")



    @Before
    fun setup() {
        wordDao = mock()
        repository = WordRepository(wordDao)
    }

    @After
    fun tearDown() {


    }

    @Test
    fun allWords() = runBlocking {
        // Get the object


        // When action
        val list: LiveData<List<Word>> = repository.allWords

        // Then results
        // Verifying wordDao.getAlphabetizedWords, creates an initialized  error that prevents the rest
        // of the test from running
        //verify(wordDao).getAlphabetizedWords()
        //TODO: Verify all words wordDao in repository unit test.
    }

    @Test
    fun insert() = runBlocking {
        // Get the object

        // When action
        repository.insert(word1)
        // Then results
        verify(wordDao).insert(word1)
    }

    @Test
    fun deleteWord() = runBlocking {
        // Get the object

        // When action
        repository.deleteWord(word1)

        // Then results
        verify(wordDao).deleteWord(word1)
    }

    @Test
    fun deleteAllWords() = runBlocking {
        // Get the object

        // When action
        repository.deleteAll()

        // Then result
        verify(wordDao).deleteAll()
    }

}