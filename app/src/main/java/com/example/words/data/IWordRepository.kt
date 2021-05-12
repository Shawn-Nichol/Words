package com.example.words.data

import androidx.lifecycle.LiveData
import com.example.words.room.Word

interface IWordRepository {
    val allWords: LiveData<List<Word>>

    suspend fun insert(word: Word)

    suspend fun deleteWord(word: Word)

    suspend fun deleteAll()

    suspend fun restore()
}