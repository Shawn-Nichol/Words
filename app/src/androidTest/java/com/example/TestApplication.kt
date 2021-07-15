package com.example


import com.example.word.di.DaggerTestAppComponent
import com.example.word.di.TestAppComponent
import com.example.words.MyApplication



/**
 * Use this application for testing so you can inject fakes and doubles.
 */
class TestApplication : MyApplication() {


    override fun initializeComponent(): TestAppComponent {
        return DaggerTestAppComponent.factory().create(this)
    }
}

// TODO: Move modules into AndroidTest
// TODO: Start by getting the MainActivity to pass a test and then start uncommenting the rest of the test
