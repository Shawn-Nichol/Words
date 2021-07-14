package com.example.words.main.fragments.newword

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.words.R
import com.example.words.main.MainFragmentFactory
import com.example.words.main.MainViewModel


import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.mock



//class NewWordFragmentUnitTest {
//
//    private lateinit var scenario: FragmentScenario<NewWordFragment>
//
//    private val mockNavController = mock(NavController::class.java)!!
//
//    @Before
//    fun setup() {
//
//        val viewModel: MainViewModel = mock(MainViewModel::class.java)
//
//        scenario = launchFragmentInContainer(
//            factory = MainFragmentFactory(viewModel),
//            fragmentArgs = null,
//            themeResId = R.style.Theme_Words
//        )
//    }
//
//    @Test
//    fun newWord_is_visible() {
//      onView(withId(R.id.et_new_word))
//            .check(ViewAssertions.matches(isDisplayed()))
//            .check(ViewAssertions.matches(isClickable()))
//    }
//
//    @Test
//    fun newWord_text_entered() {
//        onView(withId(R.id.et_new_word))
//            .perform(typeText("New Word"), closeSoftKeyboard())
//            .check(ViewAssertions.matches(withText("New Word")))
//    }
//
//    @Test
//    fun saveButton() {
//        onView(withId(R.id.button_save))
//            .check(ViewAssertions.matches(isDisplayed()))
//            .check(ViewAssertions.matches(isClickable()))
//            .check(ViewAssertions.matches(withText("Save")))
//    }
//
//    @Test
//    fun saveWord_Button_newWord_entered() {
//        // If you don't the set a mock NavController, findNavController in saveWord() will through a
//        // error.
//        scenario.onFragment { fragment ->
//            Navigation.setViewNavController(fragment.requireView(), mockNavController)
//        }
//
//        onView(withId(R.id.et_new_word))
//            .perform(typeText("New Word"))
//
//        onView(withId(R.id.button_save))
//            .perform(click())
//
////        val toast = "No word entered"
////
////        assertFalse(ShadowToast.showedToast(toast))
////        assertEquals(ShadowToast.shownToastCount(), 0)
//    }
//
//    @Test
//    fun saveWord_Button_No_newWord_ShowToast() {
//        // TODO replace with a Snackbar.
//        onView(withId(R.id.button_save))
//            .perform(click())
//
//        val toast = "No word entered"
//
////        assertTrue(ShadowToast.showedToast(toast))
////        assertEquals(ShadowToast.getTextOfLatestToast(), "No word entered")
////        assertEquals(ShadowToast.shownToastCount(), 1)
//    }
//
//    //TODO add back button press
//}