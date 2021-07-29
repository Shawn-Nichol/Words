package com.example.words.data

interface IMySharedPreferences {
    fun themeModeSave(mode: Int)
    fun themeModeGet(): Int
}