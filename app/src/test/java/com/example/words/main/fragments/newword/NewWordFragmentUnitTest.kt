package com.example.words.main.fragments.newword

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.words.R
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

    private lateinit var checkNewWord: ViewInteraction
    private lateinit var checkSaveButton: ViewInteraction

    @Before
    fun setup() {
        FragmentScenario.Companion.launchInContainer(NewWordFragment::class.java)
        checkNewWord = onView(ViewMatchers.withId(newWord))
        checkSaveButton = onView(ViewMatchers.withId(saveButton))
    }

    @Test
    fun newWord_visible() {
      checkNewWord
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

    @Test
    fun newWord_enterText() {
        checkNewWord
            .perform(ViewActions.typeText("New Word"), ViewActions.closeSoftKeyboard())
            .check(ViewAssertions.matches(ViewMatchers.withText("New Word")))
    }

    @Test
    fun saveButton() {
        checkSaveButton
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
            .check(ViewAssertions.matches(ViewMatchers.withText("Save")))
    }

    @Test
    fun saveButton_insertWord() {
        checkNewWord.perform(ViewActions.typeText("New Word"))
        checkSaveButton.perform(ViewActions.click())

        val toast = "No word entered"

        assertFalse(ShadowToast.showedToast(toast))
        assertEquals(ShadowToast.shownToastCount(), 0)
    }

    @Test
    fun saveButton_NoNewWord_ShowToast() {
        checkSaveButton.perform(ViewActions.click())

        val toast = "No word entered"

        assertTrue(ShadowToast.showedToast(toast))
        assertEquals(ShadowToast.getTextOfLatestToast(), "No word entered")
        assertEquals(ShadowToast.shownToastCount(), 1)
    }
}