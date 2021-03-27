package com.example.words.main.fragments.newword

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.words.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewWordFragmentTest {


    val newWord = R.id.et_new_word
    val saveButton = R.id.button_save


    @Before
    fun setup() {

    launchFragmentInContainer<NewWordFragment>(null, R.style.Theme_Words)


    }





    @Test
    fun saveButton() {
        onView(withId(saveButton)).check(matches(isDisplayed()))
        onView(withId(saveButton)).check(matches(isClickable()))
        onView(withId(saveButton)).check(matches(withText("Save")))
    }







    @Test
    fun saveButton_click_noWord() {
        onView(withId(saveButton)).perform(click())
// Option 1
//        onToast("No word entered").check(matches(isDisplayed()))

// Option 2
//        onView(withText(R.string.Toast_no_word)).check(matches(isDisplayed()))

// option 3
        // Is toast displayed and is the message correct?
//        onView(withText("No word entered")).inRoot(ToastMatcher2())
//            .check(matches(isDisplayed()))



    }


}

