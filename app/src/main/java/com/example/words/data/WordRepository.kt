package com.example.words.data

import androidx.lifecycle.LiveData
import com.example.words.room.InsertDBWords
import com.example.words.room.Word
import com.example.words.room.WordDao
import javax.inject.Inject

class WordRepository @Inject constructor(private val wordDao: WordDao) : IWordRepository {

    override val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    override suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    override suspend fun deleteAll() {
        wordDao.deleteAll()
    }

    override suspend fun deleteWord(word: Word) {
        wordDao.deleteWord(word)
    }

    override suspend fun restore() {
        InsertDBWords(wordDao).insert()
    }
}