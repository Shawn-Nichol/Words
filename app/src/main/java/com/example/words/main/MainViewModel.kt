package com.example.words.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.words.data.IWordRepository
import com.example.words.room.Word
import com.example.words.di.IoDispatcher
import kotlinx.coroutines.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    var repository: IWordRepository,
    @IoDispatcher var ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    // This is the job for all coroutines tarted by this ViewModel
    private val viewModelJob = SupervisorJob()

    // This is the main scope for all coroutines launched by MainViewMdoel.
    private val uiScope = CoroutineScope(ioDispatcher +  viewModelJob)

    var wordList: LiveData<List<Word>> = repository.allWords

    fun insertWord(word: Word) = uiScope.launch {
        repository.insert(word)
    }

    fun deleteWord(word: Word) = uiScope.launch {
        repository.deleteWord(word)
    }

    fun deleteAllWords() = uiScope.launch {
        repository.deleteAll()
    }

    override fun onCleared() {
        super.onCleared()
        // This cancels all Coroutines that use viewModel job when the ViewModel is closed.
        viewModelJob.cancel()
    }
}