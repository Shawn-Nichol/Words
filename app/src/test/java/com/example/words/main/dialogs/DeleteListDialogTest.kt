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
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DeleteListDialogTest {


    @Mock
    lateinit var viewModel: MainViewModel

    lateinit var scenario: FragmentScenario<DeleteListDialog>

    @Before
    fun mockSetup() {

    }

    @Before
    fun scenarioSetup() {
        MockitoAnnotations.openMocks(this)
        scenario = FragmentScenario.Companion.launch(
            factory = MainFragmentFactory(viewModel),
            fragmentClass = DeleteListDialog::class.java,
            fragmentArgs = null,
            themeResId = R.style.Theme_Words
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun message() {
        Espresso.onView(ViewMatchers.withText(R.string.dialog_delete_message))
            .inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun positiveButton_Click() {
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(android.R.id.button1), ViewMatchers.withText("YES")))
            .inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        Mockito.verify(viewModel, Mockito.times(1)).deleteAllWords()
    }

    @Test
    fun negativeButton_click() {
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(android.R.id.button2)))
            .inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))


    }
}