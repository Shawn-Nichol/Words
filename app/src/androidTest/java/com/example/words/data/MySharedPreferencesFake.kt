package com.example.words.data


import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.words.util.KEY_SHARED_PREFERENCES
import com.example.words.util.KEY_SHARED_PREFERENCES_TEST
import com.example.words.util.KEY_THEME_MODE
import org.junit.Assert.*

import org.junit.Test
import javax.inject.Inject

class MySharedPreferencesFake @Inject constructor(): IMySharedPreferences {


    private val context = ApplicationProvider.getApplicationContext<Context>()
    private var sharedPref = context.getSharedPreferences(KEY_SHARED_PREFERENCES_TEST, Context.MODE_PRIVATE)

    override fun themeModeSave(mode: Int) {
        sharedPref.edit().apply{
            putInt(KEY_THEME_MODE, mode)
            apply()
        }
    }

    override fun themeModeGet(): Int {
        return sharedPref.getInt(KEY_THEME_MODE, 1)
    }

    fun resetMode() {
        sharedPref.edit().apply() {
            putInt(KEY_THEME_MODE, 1)
            apply()
        }
    }


}
