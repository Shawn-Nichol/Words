package com.example.words.main.fragments.newword

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.words.R
import com.example.words.main.MainFragmentFactory
import com.example.words.main.MainViewModel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
class NewWordFragmentUnitTest {

    private val newWord = R.id.et_new_word
    private val saveButton = R.id.button_save

    private lateinit var checkNewWord: ViewInteraction
    private lateinit var checkSaveButton: ViewInteraction

    private lateinit var scenario: FragmentScenario<NewWordFragment>

    val mockNavController = mock(NavController::class.java)

    @Before
    fun setup() {

        val viewModel: MainViewModel = mock(MainViewModel::class.java)

        scenario = launchFragmentInContainer(
            factory = MainFragmentFactory(viewModel),
            fragmentArgs = null,
            themeResId = R.style.Theme_Words
        )

        checkNewWord = onView(ViewMatchers.withId(newWord))
        checkSaveButton = onView(ViewMatchers.withId(saveButton))
    }

    @Test
    fun `new Word is visible`() {
      checkNewWord
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

    @Test
    fun `new word text entered`() {
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
    fun `save Word Button new word entered`() {
        // If you don't the set a mock NavController, findNavController in saveWord() will through a
        // error.
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        checkNewWord.perform(ViewActions.typeText("New Word"))
        checkSaveButton.perform(ViewActions.click())

        val toast = "No word entered"

        assertFalse(ShadowToast.showedToast(toast))
        assertEquals(ShadowToast.shownToastCount(), 0)
    }

    @Test
    fun `save Word Button No New Word Show Toast`() {
        checkSaveButton.perform(ViewActions.click())

        val toast = "No word entered"

        assertTrue(ShadowToast.showedToast(toast))
        assertEquals(ShadowToast.getTextOfLatestToast(), "No word entered")
        assertEquals(ShadowToast.shownToastCount(), 1)
    }
}