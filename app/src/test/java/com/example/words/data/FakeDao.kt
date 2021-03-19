package com.example.words.data

import androidx.lifecycle.LiveData
import com.example.words.room.Word
import com.example.words.room.WordDao

class FakeDao : WordDao {
    override fun getAlphabetizedWords(): LiveData<List<Word>> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(word: Word) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWord(word: Word) {
        TODO("Not yet implemented")
    }
}