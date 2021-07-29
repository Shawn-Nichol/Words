package com.example.words.data

import org.junit.Assert.*

import org.junit.Test
import javax.inject.Inject

class MySharedPreferencesFake @Inject constructor(): IMySharedPreferences {

    private var savedValue = 1


    override fun themeModeSave(mode: Int) {
        savedValue = mode
    }

    override fun themeModeGet(): Int {
        return savedValue
    }
}