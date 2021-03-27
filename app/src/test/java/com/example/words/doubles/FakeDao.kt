package com.example.words.doubles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.words.room.Word
import com.example.words.room.WordDao

class FakeDao : WordDao {
    override fun getAlphabetizedWords(): LiveData<List<Word>> {
        val _liveData: MutableLiveData<List<Word>> = MutableLiveData<List<Word>>()
        val listLiveData: LiveData<List<Word>> = _liveData


        return listLiveData
    }

    override suspend fun insert(word: Word) {

    }

    override suspend fun deleteAll() {

    }

    override suspend fun deleteWord(word: Word) {

    }
}