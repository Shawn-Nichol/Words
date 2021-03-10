package com.example.words.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteWord(word: Word)
}