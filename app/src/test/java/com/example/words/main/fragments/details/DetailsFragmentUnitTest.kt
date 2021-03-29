package com.example.words.main.fragments.details

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.words.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DetailsFragmentUnitTest {

    lateinit var bundle: Bundle

    private val title = R.id.tv_word
    private val wordDescription = R.id.tv_word_description

    @Before
    fun setup() {
        bundle = DetailsFragmentArgs("Test").toBundle()
        launchFragmentInContainer<DetailsFragment>(bundle, R.style.Theme_Words)
    }

    @Test
    fun `TextView Title visible`() {
        onView(withId(title)).check(matches(isDisplayed()))
        onView(withId(title)).check(matches((withText("Test"))))
    }

    @Test
    fun `TextView Word Description visible`() {
        onView(withId(wordDescription)).check(matches(isDisplayed()))
        onView(withId(wordDescription)).check(matches(withText(R.string.word_description)))
    }

    @Test
    fun `bundle get and set title`() {
        val bundle = DetailsFragmentArgs("Test").toBundle()
        val myFragment = DetailsFragment()
        myFragment.word = "Test"

        assertEquals(myFragment.word, bundle.get("Word"))
    }
}