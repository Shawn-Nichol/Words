package com.example.words.main.fragments.details

import android.os.Bundle
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.words.R
import com.example.words.main.MainViewModel
import com.example.words.room.Word
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {


    lateinit var bundle: Bundle

    @Before
    fun setup() {
        // When
        bundle = DetailsFragmentArgs("Test").toBundle()
        launchFragmentInContainer<DetailsFragment>(bundle, R.style.Theme_Words)
    }

    @Test
    fun detailsActive() {
        // given

        // when details fragment launched to display
        launchFragmentInContainer<DetailsFragment>(bundle, R.style.Theme_Words)
    }


    @Test
    fun detailsFragment_Active_titleSet() {
        // Then
        onView(withId(R.id.tv_word)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_word)).check(matches(withText("Test")))
    }

    @Test
    fun detailsFragment_Active_SetDescription() {
        onView(withId(R.id.tv_word_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_word_description)).check(matches(withText(R.string.word_description)))
    }

    @Test
    fun getAndSetWord() {
        // when details fragment launched to display
        val bundle = DetailsFragmentArgs("Test").toBundle()

        val myFragment = DetailsFragment()
        myFragment.word = "Test"

        assertEquals(myFragment.word, bundle.get("Word"))

    }


}