package com.example.words.main.fragments.wordlist

import android.app.Activity
import androidx.appcompat.widget.Toolbar
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.example.words.R
import com.example.words.main.MainActivity
import com.example.words.main.fragments.wordlist.ui.RVAdapter.ItemViewHolder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@MediumTest
@ExperimentalCoroutinesApi
class WordListFragmentTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun rv_scrollTo_click_back_Press() = runBlockingTest {
        onView(withId(R.id.rv_container)).perform(
            RecyclerViewActions.scrollTo<ItemViewHolder>(
                withText("Test word: 40")
            )
        )

        onView(withText("Test word: 40")).perform(ViewActions.click())

        onView(withId(R.id.tv_word_title)).check(ViewAssertions.matches(isDisplayed()))
        onView(withText("Test word: 40")).check(ViewAssertions.matches(isDisplayed()))

        onView(withContentDescription(scenario.getToolbarNavigationContentDescription())).perform(
            ViewActions.click()
        )
        onView(withText("WordListFragment")).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun rv_Swipe_toRight() = runBlockingTest {
        onView(withId(R.id.rv_container)).perform(
            RecyclerViewActions.actionOnItem<ItemViewHolder>(
                withText("Test word: 43"), ViewActions.swipeRight()
            )
        )

        onView(withId(R.id.rv_container)).check(ViewAssertions.matches(not(withText("Test word: 43"))))
    }

    @Test
    fun rv_Swipe_toLeft() = runBlockingTest {
        onView(withId(R.id.rv_container)).perform(
            RecyclerViewActions.actionOnItem<ItemViewHolder>(
                withText("Test word: 43"), ViewActions.swipeLeft()
            )
        )

        onView(withId(R.id.rv_container)).check(ViewAssertions.matches(not(withText("Test word: 43"))))
    }

    @Test
    fun fab_click_navigateToNewItemFragment() {
        onView(withId(R.id.fab_new_word)).perform(ViewActions.click())

        onView(withText("NewWordFragment")).check(ViewAssertions.matches(isDisplayed()))

        onView(withContentDescription(scenario.getToolbarNavigationContentDescription())).perform(
            ViewActions.click()
        )
        onView(withText("WordListFragment")).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun fab_ScrollUp_HideFAB() {
        // when
        onView(withId(R.id.rv_container)).perform(
            RecyclerViewActions.actionOnItem<ItemViewHolder>(
                withText("Test word: 20"), ViewActions.swipeUp())
            )

        // Then
        onView(withId(R.id.fab_new_word)).check(ViewAssertions.matches(not(isDisplayed())))
    }

    @Test
    fun fab_ScrollDown_ShowFAB() {
        fab_ScrollUp_HideFAB()

        onView(withId(R.id.rv_container)).perform(RecyclerViewActions.actionOnItem<ItemViewHolder>(
            withText("Test word: 05"), ViewActions.swipeDown()
        ))

        onView(withId(R.id.fab_new_word)).check(ViewAssertions.matches(isDisplayed()))
    }

    fun <T : Activity> ActivityScenario<T>.getToolbarNavigationContentDescription()
            : String {
        var description = ""
        onActivity {
            description =
                it.findViewById<Toolbar>(R.id.toolbar).navigationContentDescription as String
        }
        return description
    }
}