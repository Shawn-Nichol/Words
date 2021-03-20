package com.example.words

import androidx.lifecycle.MutableLiveData
import com.example.words.data.FakeDao
import com.example.words.data.WordRepository
import com.example.words.room.Word
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WordRepositoryTest {

    lateinit var repository: WordRepository
    lateinit var wordDao: FakeDao
    var word1 = Word("Word1")


    @Before
    fun setup() {
        wordDao = mock()
        repository = WordRepository(wordDao)
    }

//    @Test
//    fun allWords() = runBlocking {
//        // Get the object
//
//        // When action
//        repository.allWords
//        // Then results
//        verify(wordDao).getAlphabetizedWords()
//    }

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