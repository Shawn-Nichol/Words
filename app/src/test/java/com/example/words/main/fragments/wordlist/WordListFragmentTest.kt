package com.example.words.main.fragments.wordlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.words.R
import com.example.words.main.MainFragmentFactory
import com.example.words.main.MainViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class WordListFragmentTest {

    // Executes task sin the Architecture component in the same thread.
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var scenario: FragmentScenario<WordListFragment>

    private lateinit var viewModel: MainViewModel

    private val mockNavController = mock(NavController::class.java)

    @Before
    fun setup() {
        viewModel = mock(MainViewModel::class.java)

        scenario = launchFragment(
            factory = MainFragmentFactory(viewModel),
            fragmentArgs = null,
            themeResId = R.style.Theme_Words,
            initialState = Lifecycle.State.RESUMED
        )

        // Todo all test currently pass but there some probelm with the main looper causing the tests to fail.
    }

    @Test
    fun `Menu Delete Words`() {
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        // Open menu
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext())

        onView(withText(R.string.delete_words))
            .check(matches(isDisplayed()))
            .perform(click())

        verify(mockNavController).navigate(R.id.action_dest_wordListFragment_to_deleteListDialog)
    }

    @Test
    fun `Menu Restore list`() {
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        // Open menu
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext())

        onView(withText(R.string.restore_list))
            .check((matches(isDisplayed())))
            .perform(click())

        verify(mockNavController).navigate(R.id.action_dest_wordListFragment_to_restoreWordListDialog)
    }

    @Test
    fun `Menu Dark Mode`() {
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext())

        onView(withText(R.string.dark_mode))
            .check(matches(isDisplayed()))

    }
}