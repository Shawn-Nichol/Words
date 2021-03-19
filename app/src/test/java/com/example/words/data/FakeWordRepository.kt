package com.example.words.data

import android.provider.UserDictionary
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.words.room.Word

class FakeWordRepository : IWordRepository {

    private val _allWords = MutableLiveData<MutableList<Word>>()
    override val allWords: LiveData<List<Word>> = _allWords


    override suspend fun insert(word: Word) {
        _allWords.postValue(word)
    }

    override suspend fun deleteAll() {
        allWords.rem
    }

    override suspend fun deleteWord(word: Word) {
        TODO("Not yet implemented")
    }


}