package com.example.words.main.dialogs

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import com.example.words.R
import com.example.words.main.MainFragmentFactory
import com.example.words.main.MainViewModel
import org.hamcrest.Matchers
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RestoreWordListDialogTest {

    @Mock
    lateinit var viewModel: MainViewModel

    private lateinit var scenario: FragmentScenario<RestoreWordListDialog>

    @Before
    fun setUpMock() {
        MockitoAnnotations.openMocks(this)
    }

    @Before
    fun setupScenario() {
        scenario = FragmentScenario.launch(
            factory = MainFragmentFactory(viewModel),
            fragmentArgs = null,
            fragmentClass = RestoreWordListDialog::class.java,
            themeResId = R.style.Theme_Words
        )
    }


    @After
    fun tearDown() {

    }

    @Test
    fun message() {
        Espresso.onView(ViewMatchers.withText(R.string.dialog_restore_message))
            .inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun positiveButton_click() {
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(android.R.id.button1), ViewMatchers.withText("YES")))
            .inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        Mockito.verify(viewModel, Mockito.times(1)).restoreList()
    }

    @Test
    fun negativeButton_click() {
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(android.R.id.button2), ViewMatchers.withText("NO")))
            .inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))

    }
}