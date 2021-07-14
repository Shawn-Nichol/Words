package com.example.words.main.dialogs

import androidx.fragment.app.testing.launchFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.words.R
import com.example.words.main.MainViewModel
import com.nhaarman.mockitokotlin2.verify
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RestoreWordListDialogUnitTest {

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {

        viewModel = mock(MainViewModel::class.java)

        launchFragment(themeResId = R.style.Theme_Words) {
            return@launchFragment RestoreWordListDialog(viewModel)
        }
    }

    @Test
    fun `show Title`() {
        onView(withText(R.string.dialog_restore_title))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }

    @Test
    fun `show Message`() {
        onView(withText(R.string.dialog_restore_message))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }

    @Test
    fun `Positive Button`() {
        onView(allOf(withId(android.R.id.button1), withText(R.string.dialog_confirm)))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .perform(click())

        verify(viewModel).restoreList()
    }
}