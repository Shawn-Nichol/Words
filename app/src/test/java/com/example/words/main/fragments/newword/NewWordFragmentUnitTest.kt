package com.example.words.main.fragments.newword

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.words.R
import com.example.words.main.MainActivity
import com.example.words.main.MainViewModel
import com.example.words.main.fragments.details.DetailsFragment
import com.example.words.main.fragments.details.DetailsFragmentArgs
import com.example.words.room.Word
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.whenever
import org.junit.After
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

    private lateinit var mViewModel: MainViewModel

    @Before
    fun setup() {

// Launches Fragment
        FragmentScenario.Companion.launchInContainer(NewWordFragment::class.java)

//        Idea 1, to load viewModel
//        mViewModel = mock()

//        val scenario = launchFragmentInContainer<NewWordFragment>()
//        scenario.onFragment {fragment ->
//            fragment.viewModel
//        }

        // Idea two
//        NewWordFragment.viewModel = mViewModel

    }
    // Fragment Factory


    @After
    fun close() {

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

        val toast = "No word entered"

        assertFalse(ShadowToast.showedToast(toast))
        assertEquals(ShadowToast.shownToastCount(), 0)
    }

    @Test
    fun saveButton_NoNewWord_ShowToast() {
        Espresso.onView(ViewMatchers.withId(saveButton)).perform(ViewActions.click())

        val toast = "No word entered"

        assertTrue(ShadowToast.showedToast(toast))
        assertEquals(ShadowToast.getTextOfLatestToast(), "No word entered")
        assertEquals(ShadowToast.shownToastCount(), 1)
    }


}