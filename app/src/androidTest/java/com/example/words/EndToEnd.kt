package com.example.words

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.filters.LargeTest
import com.example.words.main.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule

@LargeTest
@ExperimentalCoroutinesApi
class EndToEnd {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var scenario: ActivityScenario<MainActivity>


    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun close() {
        scenario.close()
    }

    // Add a new Item, Find new Item in list
    // click on New Item,

}