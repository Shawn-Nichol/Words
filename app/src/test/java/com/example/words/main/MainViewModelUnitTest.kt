package com.example.words.main


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.filters.SmallTest
import com.example.words.data.MySharedPreferences
import com.example.words.data.WordRepository
import com.example.words.data.room.Word
import com.example.words.testutils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@SmallTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MainViewModelUnitTest {


    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    // Runs livedata on the main UI thread and waits for results.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var wordRepository: WordRepository

    @Mock
    private lateinit var sharedPrefRepository: MySharedPreferences

    private lateinit var viewModel: MainViewModel

    private lateinit var word1: Word

    // Passes a Test dispatcher into the ViewModel for testing coroutine scopes.
    private val testDispatcher: TestCoroutineDispatcher = mainCoroutineRule.dispatcher

    @Before
    fun setup() {

        MockitoAnnotations.openMocks(this)

        viewModel = MainViewModel(wordRepository, sharedPrefRepository, testDispatcher)

        word1 = Word("Word1")
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `get word list from repository`() {
        // Given

        // When action
        viewModel.wordList

        // Then
        verify(wordRepository, times(1)).allWords
    }

    @Test
    fun `insert a word to the database`() = mainCoroutineRule.runBlockingTest {

        // When action, input or output
        viewModel.insertWord(word1)

        // Then
        verify(wordRepository, times(1)).insert(word1)
    }

    @Test
    fun `delete a word from the database`() = mainCoroutineRule.runBlockingTest {
        // Given, setup objects

        // When action
        viewModel.deleteWord(word1)

        // Then results
        verify(wordRepository, times(1)).deleteWord(word1)
    }

    @Test
    fun `delete all the  word from the database`() = mainCoroutineRule.runBlockingTest {
        // Given, setup objects

        // When action
        viewModel.deleteAllWords()

        // Then results
        verify(wordRepository, times(1)).deleteAll()
    }

    @Test
    fun `restore original words in database`() = mainCoroutineRule.runBlockingTest {
        viewModel.restoreList()

        verify(wordRepository, times(1)).restore()
    }

    @Test
    fun `themeModeLoad Get Value`() {
        // given
        val mode = 1
        `when`(sharedPrefRepository.ThemeModeGet()).thenReturn(mode)

        // when
        val themeMode = viewModel.themeModeLoad()

        // Then
        verify(sharedPrefRepository).ThemeModeGet()
        Assert.assertEquals(themeMode, mode)
    }

    @Test
    fun `themeMode Save Mode`() {
        // given
        val mode = 1
        // when
        viewModel.themeModeSave(mode)
        // then
        verify(sharedPrefRepository).ThemeModeSave(mode)
    }
}