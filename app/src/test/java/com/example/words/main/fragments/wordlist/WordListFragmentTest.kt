package com.example.words.main.fragments.wordlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.words.R
import com.example.words.main.MainFragmentFactory
import com.example.words.main.MainViewModel
import com.example.words.room.Word
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation



@RunWith(RobolectricTestRunner::class)
class WordListFragmentTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var scenario: FragmentScenario<WordListFragment>
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        val wordOne = Word("WordOne")
        val wordTwo = Word("WordTwo")
        val list = mutableListOf(wordOne, wordTwo)
        val liveData = MutableLiveData<List<Word>>(list)

        viewModel = mock(MainViewModel::class.java)
        `when`(viewModel.wordList).thenReturn(liveData)

        scenario = launchFragmentInContainer(
            factory = MainFragmentFactory(viewModel),
            fragmentArgs = null,
            themeResId = R.style.Theme_Words,
            initialState = Lifecycle.State.RESUMED
        )
    }

    @Test
    fun `FAB properties`() {
        val fab = onView(ViewMatchers.withId(R.id.fab_new_word))

        fab
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(isEnabled()))
    }

    @Test
    fun `FAB clicked`() {
        val fab = onView(ViewMatchers.withId(R.id.fab_new_word))
        val mockNavController = mock(NavController::class.java)


        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        fab.perform(ViewActions.click())

        val action = R.id.action_wordListFragment_to_newWordFragment
        verify(mockNavController).navigate(
            action
        )

    }

    @Test
    fun `RecyclerView properties`() {
        val recyclerView = onView(ViewMatchers.withId(R.id.recyclerView))

        recyclerView
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isEnabled()))
    }

    @Test
    fun `initialise submitList`() {
        verify(viewModel).wordList
    }

    @Test
    fun `word Item one`() {
        val wordItem = onView(ViewMatchers.withText("WordOne"))

        wordItem
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isEnabled()))
            .check(ViewAssertions.matches(isClickable()))
    }

    @Test
    fun `word Item Two`() {
        val wordItem = onView(ViewMatchers.withText("WordTwo"))


        wordItem
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isEnabled()))
            .check(ViewAssertions.matches(isClickable()))
    }

    @Test
    fun `initRecyclerView called`() {

    }
}