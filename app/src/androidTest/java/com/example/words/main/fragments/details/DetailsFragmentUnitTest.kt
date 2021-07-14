package com.example.words.main.fragments.details

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.words.R
import com.example.words.main.MainFragmentFactory
import com.example.words.main.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DetailsFragmentUnitTest {

    private lateinit var bundle: Bundle

    private lateinit var scenario: FragmentScenario<DetailsFragment>

    @Before
    fun setup() {
        val viewModel: MainViewModel = mock(MainViewModel::class.java)
        bundle = DetailsFragmentArgs("Test").toBundle()

        scenario  = launchFragmentInContainer(
            factory = MainFragmentFactory(viewModel),
            fragmentArgs = bundle,
            themeResId = R.style.Theme_Words
        )
    }

    @Test
    fun `TextView Word Title loaded`() {
        onView(withId(R.id.tv_word))
            .check(matches(isDisplayed()))
            .check(matches((withText("Test"))))
    }

    @Test
    fun `TextView Word Description loaded`() {
        onView(withId(R.id.tv_word_description))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.word_description)))
    }

    @Test
    fun `bundle get Word`() {
        scenario.onFragment {
            // Make sure to pass
            assertEquals(it.word, bundle.get("key_word"))
        }
    }

}