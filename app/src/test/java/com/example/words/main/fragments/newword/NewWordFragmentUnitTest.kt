package com.example.words.main.fragments.newword

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.words.R
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
class NewWordFragmentUnitTest {

    private val newWord = R.id.et_new_word
    private val saveButton = R.id.button_save

    @Before
    fun setup() {
        FragmentScenario.Companion.launchInContainer(NewWordFragment::class.java)
    }

    @Test
    fun newWord_visible() {
        Espresso.onView(ViewMatchers.withId(newWord))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(newWord))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

    @Test
    fun newWord_enterText() {
        Espresso.onView(ViewMatchers.withId(newWord))
            .perform(ViewActions.typeText("New Word"), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(newWord))
            .check(ViewAssertions.matches(ViewMatchers.withText("New Word")))
    }

    @Test
    fun saveButton() {
        Espresso.onView(ViewMatchers.withId(saveButton))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(saveButton))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))

        Espresso.onView(ViewMatchers.withId(saveButton))
            .check(ViewAssertions.matches(ViewMatchers.withText("Save")))
    }

    @Test
    fun saveButton_insertWord() {
        Espresso.onView(ViewMatchers.withId(newWord)).perform(ViewActions.typeText("New Word"))
        Espresso.onView(ViewMatchers.withId(saveButton)).perform(ViewActions.click())
    }

    @Test
    fun saveButton_NoNewWord_ShowToast() {
        Espresso.onView(ViewMatchers.withId(saveButton)).perform(ViewActions.click())

        val toast = "No word entered"

        assertTrue(ShadowToast.showedToast(toast))
        assertEquals(ShadowToast.shownToastCount(), 1)
    }


}