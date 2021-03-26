package com.example.words.main.fragments.newword

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.words.R
import com.example.words.ToastMatcher
import org.hamcrest.core.IsNot.not
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit.rule


@RunWith(AndroidJUnit4::class)
class NewWordFragmentTest {


    val newWord = R.id.et_new_word
    val saveButton = R.id.button_save


    @Before
    fun setup() {
        launchFragmentInContainer<NewWordFragment>(null, R.style.Theme_Words)


    }

    @Test
    fun newWord_visible() {
        onView(withId(newWord)).check(matches(isDisplayed()))
        onView(withId(newWord)).check(matches(isClickable()))
    }

    @Test
    fun newWord_enterText() {
        onView(withId(newWord)).perform(typeText("New Word"), closeSoftKeyboard())

        onView(withId(newWord)).check(matches(withText("New Word")))
    }

    @Test
    fun saveButton() {
        onView(withId(saveButton)).check(matches(isDisplayed()))
        onView(withId(saveButton)).check(matches(isClickable()))
        onView(withId(saveButton)).check(matches(withText("Save")))
    }

    @Test
    fun saveButton_insertWord() {
        onView(withId(newWord)).perform(typeText("New Word"))

        onView(withId(saveButton)).perform(click())


    }


    @Test
    fun saveButton_click_noWord() {
        onView(withId(saveButton)).perform(click())

//        onView(withText(startsWith("Clicked:"))).inRoot(
//            withDecorView(
//                not(`is`(rule.getActivity().getWindow().getDecorView()))
//            )
//        ).check(matches(isDisplayed()))
//
//        onView(withText(R.string.Toast_no_word)).check(matches(isDisplayed()))
//       onView(withText(R.string.Toast_no_word)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
//        onView(withText(R.string.toast)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        onView(withText(R.string.Toast_no_word)).inRoot(
            withDecorView(
                not(
                    mActivityRule.getActivity().getWindow().getDecorView()
                )
            )
        )
            .check(matches(isDisplayed()));
    }


}

