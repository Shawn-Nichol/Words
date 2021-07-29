package com.example.words.main.fragments.wordlist

import android.os.Looper.getMainLooper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.SmallTest
import com.example.words.R
import com.example.words.data.room.Word
import com.example.words.main.MainFragmentFactory
import com.example.words.main.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.LooperMode

@SmallTest
@RunWith(RobolectricTestRunner::class)
class WordListFragmentUnitTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var navController: NavController

    private val liveData = MutableLiveData<List<Word>>()

    private lateinit var scenario: FragmentScenario<WordListFragment>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        // Shadow can modify or extend the corresponding class in Android
//        shadowOf(getMainLooper()).idle()

        doReturn(liveData).`when`(viewModel).wordList

        scenario = launchFragmentInContainer(
            factory = MainFragmentFactory(viewModel),
            fragmentArgs = null,
            themeResId = R.style.Theme_Words
        )

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.view!!, navController)
        }
    }
    
    // FAB Visible, CLick
    @Test
    fun `fab loaded`() {
        Espresso.onView(ViewMatchers.withId(R.id.fab_new_word))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        verify(navController, times(1))
            .navigate(R.id.action_wordListFragment_to_newWordFragment)
    }

    // RecyclerViewVisible.
    @Test
    fun `RecyclerView visible`() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_container))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    // Menu Delete all
    @Test
    fun `Menu Delete all`() {
        Espresso.openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext())

        Espresso.onView(ViewMatchers.withText(R.string.delete_words))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        verify(navController, times(1))
            .navigate(R.id.action_dest_wordListFragment_to_deleteListDialog
        )
    }
    // Menu Restore list
    @Test
    fun `Menu Restore list`() {
        Espresso.openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext())

        Espresso.onView(ViewMatchers.withText(R.string.restore_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        verify(navController, times(1))
            .navigate(R.id.action_dest_wordListFragment_to_restoreWordListDialog)
    }
    // Menu Dark list

    @Test
    fun `Menu DarkMode on`() {
        Espresso.openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext())
        Espresso.onView(ViewMatchers.withText(R.string.dark_mode))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())


    }

    @Test
    fun `Menu DarkMode off`() {

    }
}