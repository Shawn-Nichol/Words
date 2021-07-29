package com.example.words.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.words.data.IMySharedPreferences
import com.example.words.data.IWordRepository
import com.example.words.data.room.Word
import com.example.words.di.IoDispatcher
import kotlinx.coroutines.*
import javax.inject.Inject

open class MainViewModel @Inject constructor(
    var repository: IWordRepository,
    var sharedPref: IMySharedPreferences,
    @IoDispatcher var ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    // This is the job for all coroutines tarted by this ViewModel
    private val viewModelJob = SupervisorJob()

    // This is the main scope for all coroutines launched by MainViewModel.
    private val viewModelScope = CoroutineScope(ioDispatcher +  viewModelJob)

    var wordList: LiveData<List<Word>> = repository.allWords

    var themeMode: Int = 1

    init {
        themeModeLoad()
    }

    override fun onCleared() {
        super.onCleared()
        // This cancels all Coroutines that use the viewModelJob when the ViewModel is closed.
        viewModelJob.cancel()
    }

    fun deleteWord(word: Word) = viewModelScope.launch {
        repository.deleteWord(word)
    }

    fun deleteAllWords() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun insertWord(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

    fun restoreList() = viewModelScope.launch {
        repository.restore()
    }

    fun themeModeLoad() {
        themeMode = sharedPref.themeModeGet()
    }

    fun themeModeSave() {
        sharedPref.themeModeSave(themeMode)
    }
}