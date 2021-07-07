package com.example.words.main

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.words.R
import org.junit.After
import org.junit.Before
import org.junit.Test


class MainActivityTest {


    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun close() {
        scenario.close()
    }

    @Test
    fun mainActivityTest() {
        Espresso.onView(ViewMatchers.withId(R.id.main_container))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}