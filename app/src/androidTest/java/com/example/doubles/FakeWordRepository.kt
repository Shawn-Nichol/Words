package com.example.doubles


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.words.data.IWordRepository
import com.example.words.data.room.Word
import javax.inject.Inject

class FakeWordRepository @Inject constructor(): IWordRepository {

    override val allWords: LiveData<List<Word>> = MutableLiveData<List<Word>>()

    val listAllWords = mutableListOf<Word>()


    override suspend fun insert(word: Word) {

    }

    override suspend fun deleteAll() {

    }

    override suspend fun deleteWord(word: Word) {
        word
    }

    override suspend fun restore() {

    }


}