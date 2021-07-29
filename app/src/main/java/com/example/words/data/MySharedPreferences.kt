package com.example.words.data

import android.content.Context
import com.example.words.util.KEY_SHARED_PREFERENCES
import com.example.words.util.KEY_THEME_MODE
import javax.inject.Inject

class MySharedPreferences @Inject constructor(context: Context) : IMySharedPreferences {

    private val sharedPref = context.getSharedPreferences(KEY_SHARED_PREFERENCES,  Context.MODE_PRIVATE)

    override fun themeModeSave(saveMode: Int) {
        sharedPref.edit().apply{
            putInt(KEY_THEME_MODE, saveMode)
            apply()
        }
    }

    override fun themeModeGet(): Int {
        return sharedPref.getInt(KEY_THEME_MODE, 1)
    }
}