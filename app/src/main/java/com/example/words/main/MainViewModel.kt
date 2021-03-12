package com.example.words.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.words.room.Word
import com.example.words.room.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(var repository: WordRepository): ViewModel() {

    var wordList: LiveData<List<Word>> = repository.allWords

    fun insertWord(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }

    fun deleteWord(word: Word) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteWord(word)
    }

    fun deleteAllWords() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

}