package com.example.words.main.fragments.details

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.words.R
import com.example.words.main.MainFragmentFactory
import com.example.words.main.MainViewModel
import junit.framework.Assert.assertEquals


import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DetailsFragmentUnitTest {



    lateinit var bundle: Bundle

    private val title = R.id.tv_word
    private val wordDescription = R.id.tv_word_description

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
    fun `TextView Title visible`() {
        onView(withId(title))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

            .check(matches((withText("Test"))))
    }

    @Test
    fun `TextView Word Description visible`() {
        onView(withId(wordDescription))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.word_description)))
    }

    @Test
    fun `bundle get and set title`() {
        scenario.onFragment {
            assertEquals(it.word, bundle.get("Word"))
        }
    }
}