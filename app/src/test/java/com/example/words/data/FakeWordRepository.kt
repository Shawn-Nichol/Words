package com.example.words.data


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.words.room.Word

class FakeWordRepository : IWordRepository {

    override val allWords: LiveData<List<Word>> = MutableLiveData<List<Word>>()
    val listAllWords = mutableListOf<Word>()


    override suspend fun insert(word: Word) {

    }

    override suspend fun deleteAll() {

    }

    override suspend fun deleteWord(word: Word) {

    }


}