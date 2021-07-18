package com.example.words.main.fragments.newword

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.SmallTest
import com.example.words.R
import com.example.words.main.MainFragmentFactory
import com.example.words.main.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast

@SmallTest
@RunWith(RobolectricTestRunner::class)
class NewWordFragmentUnitTest {

    private lateinit var scenario: FragmentScenario<NewWordFragment>

    @Mock
    private lateinit var mockNavController: NavController

    @Mock
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        scenario = launchFragmentInContainer(
            factory = MainFragmentFactory(viewModel),
            fragmentArgs = null,
            themeResId = R.style.Theme_Words
        )
    }

    @Test
    fun `editText new Word is visible`() {
        Espresso.onView(ViewMatchers.withId(R.id.et_new_word))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

    @Test
    fun `editText new Word enter text`() {
        Espresso.onView(ViewMatchers.withId(R.id.et_new_word))
            .perform(ViewActions.typeText("New Word"))
            .check(ViewAssertions.matches(ViewMatchers.withText("New Word")))
    }

    @Test
    fun `Button Save`() {
        Espresso.onView(ViewMatchers.withId(R.id.button_save))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
            .check(ViewAssertions.matches(ViewMatchers.withText("Save")))
    }

    @Test
    fun `Save Word Button new word entered`() {
        // If you don't set a mock NavController, findNavController will throw an error.
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.et_new_word))
            .perform(ViewActions.typeText("New Word"))

        Espresso.onView(ViewMatchers.withId(R.id.button_save))
            .perform(ViewActions.click())

        val toast = "No word entered"

        Assert.assertFalse(ShadowToast.showedToast(toast))
        Assert.assertEquals(ShadowToast.shownToastCount(), 0)
    }

    @Test
    fun `save Word Button No new Word, show Toast`() {
        Espresso.onView(ViewMatchers.withId(R.id.button_save))
            .perform(ViewActions.click())

        val toast = "No word entered"

        Assert.assertTrue(ShadowToast.showedToast(toast))
        Assert.assertEquals(ShadowToast.getTextOfLatestToast(), "No word entered")
        Assert.assertEquals(ShadowToast.shownToastCount(), 1)
    }
}