package com.example.words.data

interface IMySharedPreferences {
    fun ThemeModeSave(mode: Int)
    fun ThemeModeGet(): Int
}