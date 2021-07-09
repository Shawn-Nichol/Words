package com.example.words.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.words.data.IWordRepository
import com.example.words.data.room.Word
import com.example.words.di.IoDispatcher
import kotlinx.coroutines.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    var repository: IWordRepository,
    @IoDispatcher var ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    // This is the job for all coroutines tarted by this ViewModel
    private val viewModelJob = SupervisorJob()

    // This is the main scope for all coroutines launched by MainViewModel.
    private val viewModelScope = CoroutineScope(ioDispatcher +  viewModelJob)

    var wordList: LiveData<List<Word>> = repository.allWords

    fun insertWord(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

    fun deleteWord(word: Word) = viewModelScope.launch {
        repository.deleteWord(word)
    }

    fun deleteAllWords() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun restoreList() = viewModelScope.launch {
        repository.restore()
    }

    override fun onCleared() {
        super.onCleared()
        // This cancels all Coroutines that use the viewModelJob when the ViewModel is closed.
        viewModelJob.cancel()
    }
}