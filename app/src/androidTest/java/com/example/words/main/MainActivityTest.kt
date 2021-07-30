package com.example.words.main

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import com.example.words.R
import com.example.words.data.MySharedPreferencesFake
import com.example.words.util.MODE_DAY
import com.example.words.util.MODE_NIGHT
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.runner.RunWith


@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var scenario: ActivityScenario<MainActivity>


    private lateinit var context: Context

    @Before
    fun resetMode() {
        val sharedPref = MySharedPreferencesFake()
        sharedPref.resetMode()
    }

    @Before
    fun scenario() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }
    @Before
    fun context() {
        context = ApplicationProvider.getApplicationContext()
    }

    @After
    fun close() {
        scenario.close()
    }

    @Test
    fun mainActivityLoadContainer() {
        Espresso.onView(ViewMatchers.withId(R.id.main_container))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }



    @Test
    fun menuDarkModeOn() {

        var currentMode = 0

        Espresso.openActionBarOverflowOrOptionsMenu(context)

        Espresso.onView(ViewMatchers.withText(R.string.dark_mode_off))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        Espresso.openActionBarOverflowOrOptionsMenu(context)

        Espresso.onView(ViewMatchers.withText(R.string.dark_mode_on))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        scenario.onActivity {
            currentMode = if(it.resources.configuration.isNightModeActive) MODE_NIGHT else MODE_DAY
        }

        Assert.assertEquals(MODE_NIGHT, currentMode)
    }



    @Test
    fun menuDarkModeOff() {
        var currentMode = 0
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        Espresso.onView(ViewMatchers.withText(R.string.dark_mode_off)).perform(ViewActions.click())
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        Espresso.onView(ViewMatchers.withText(R.string.dark_mode_on)).perform(ViewActions.click())

        Espresso.openActionBarOverflowOrOptionsMenu(context)
        Espresso.onView(ViewMatchers.withText(R.string.dark_mode_off)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        scenario.onActivity {
            currentMode = if(it.resources.configuration.isNightModeActive) MODE_NIGHT else MODE_DAY
        }

        Assert.assertEquals(MODE_DAY, currentMode)

    }
}