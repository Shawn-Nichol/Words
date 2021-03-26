package com.example.words.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.words.data.IWordRepository
import com.example.words.room.Word
import com.example.words.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    var repository: IWordRepository,
    @IoDispatcher var ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    var wordList: LiveData<List<Word>> = repository.allWords

    fun insertWord(word: Word) = viewModelScope.launch(ioDispatcher) {
        repository.insert(word)
    }

    fun deleteWord(word: Word) = viewModelScope.launch(ioDispatcher) {
        repository.deleteWord(word)
    }

    fun deleteAllWords() = viewModelScope.launch(ioDispatcher) {
        repository.deleteAll()
    }

}