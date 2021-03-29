package com.example.words.data

import com.example.words.doubles.FakeDao
import com.example.words.room.Word
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WordRepositoryUnitTest {
    lateinit var repository: WordRepository
    lateinit var wordDao: FakeDao
    var word1 = Word("Word1")


    @Before
    fun setup() {
        wordDao = mock()
        repository = WordRepository(wordDao)
    }

    @Test
    fun allWords() = runBlocking {
        // Get the object

        // When action
//        repository.allWords
        // Then results
//        verify(wordDao).getAlphabetizedWords()
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