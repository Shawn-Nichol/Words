package com.example.words.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/*
-MainCoroutineRule extends TestWatcher, which implements the TestRule interface. This is what
makes MainCoroutineRule a JUnit rule.
-The starting and finished methods match what you wrote in your @Before and @After functions. They also
run before and after each test.
-MainCoroutineRule also implements TestCoroutineScope, to which you pass in the TestCoroutineDispatcher.
This gives MainCoroutineRule the ability to control coroutine timing (using the TestCoroutineDispatcher you pass in). You'll see an example of this in the next step.
 */
@ExperimentalCoroutinesApi
class MainCoroutineRule(
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}