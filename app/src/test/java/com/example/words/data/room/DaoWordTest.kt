package com.example.words.data.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.words.testutils.MainCoroutineRule
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


@SmallTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class DaoWordTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var wordDatabase: WordRoomDatabase
    private lateinit var wordDao: WordDao

    @Mock
    private lateinit var testObserver: Observer<List<Word>>

    private val word1 = Word("Test1")
    private val word2 = Word("Test2")


    @Before
    fun setup() {

        MockitoAnnotations.openMocks(this)

        // Room Builder creates an in memory Database. Information stored in an in-memory database disappears when tests finish
        wordDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            WordRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()


        wordDao = wordDatabase.wordDao()

        insertWords()
    }

    @After
    fun closeDb() {
        wordDao.getAlphabetizedWords().removeObserver(testObserver)
        wordDatabase.close()
    }

    @Test
    fun insertWord() = runBlockingTest {

        wordDao.getAlphabetizedWords().observeForever(testObserver)

        val listClass = ArrayList::class.java as Class<ArrayList<Word>>
        // ArgumentCaptor to capture the value in onChanged(). Using an arguemntCaptor from Mockito
        // allows you to make more complex assertions on value than equals
        val argumentCaptor = ArgumentCaptor.forClass(listClass)

        verify(testObserver).onChanged(argumentCaptor.capture())

        val capturedArgument = argumentCaptor.value
        assertTrue(capturedArgument.size == 2)
        assertTrue(capturedArgument.containsAll(listOf(word1, word2)))
    }


    @Test
    fun deleteWord() = runBlockingTest {

        wordDao.deleteWord(word1)


        wordDao.getAlphabetizedWords().observeForever(testObserver)

        val listClass = ArrayList::class.java as Class<ArrayList<Word>>
        val argumentCaptor = ArgumentCaptor.forClass(listClass)

        verify(testObserver).onChanged(argumentCaptor.capture())

        val captureArgument = argumentCaptor.value

        assertTrue(captureArgument.size == 1)
        assertFalse(captureArgument.contains(word1))

    }

    @Test
    fun deleteAll() = runBlocking {

        wordDao.deleteAll()

        wordDao.getAlphabetizedWords().observeForever(testObserver)

        val listClass = ArrayList::class.java as Class<ArrayList<Word>>
        val argumentCaptor = ArgumentCaptor.forClass(listClass)

        verify(testObserver).onChanged(argumentCaptor.capture())

        val captureArgument = argumentCaptor.value

        assertTrue(captureArgument.isEmpty())
    }

    private fun insertWords() = runBlockingTest {
        wordDao.insert(word1)
        wordDao.insert(word2)
    }
}