package com.example.words.di


import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.TestApplication



/**
 * A custom [AndroidJUnitRunner] used to replace the application used in tests with a
 * [MyApplicationAndroidTest].
 */
class MyCustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}
