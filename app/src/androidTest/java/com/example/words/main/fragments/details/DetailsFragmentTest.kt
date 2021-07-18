package com.example.words.main.fragments.details

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.words.R
import com.example.words.main.MainFragmentFactory
import com.example.words.main.MainViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


/**
 * Mock Aren't working in this test not sure it will
 */
@RunWith(MockitoJUnitRunner::class)
class DetailsFragmentTest {

    private lateinit var  bundle: Bundle
    private lateinit var scenario: FragmentScenario<DetailsFragment>


    @Mock
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {

//        MockitoAnnotations.openMocks(this)

        bundle = DetailsFragmentArgs("Test").toBundle()

        scenario = launchFragmentInContainer(
            factory = MainFragmentFactory(viewModel),
            fragmentArgs = bundle,
            themeResId = R.style.Theme_Words
        )
    }

    @Test
    fun textView_WordDescription() {
        Espresso.onView(ViewMatchers.withId(R.id.tv_word))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
