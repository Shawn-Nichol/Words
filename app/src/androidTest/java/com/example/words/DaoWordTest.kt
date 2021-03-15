package com.example.words


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.words.room.Word
import com.example.words.room.WordDao
import com.example.words.room.WordRoomDatabase
import com.nhaarman.mockitokotlin2.firstValue
import com.nhaarman.mockitokotlin2.lastValue
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class DaoWordTest {


    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var wordDatabase: WordRoomDatabase
    private lateinit var wordDao: WordDao




    @Before
    fun setup() {
        // Room Builder creates an in memory Database. Information stored in an in-memory database disappears when tests finish
        wordDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            WordRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()


        wordDao = wordDatabase.wordDao()
    }

    @After
    fun closeDb() {
        wordDatabase.close()
    }

    @Test
    fun emptyList() {
        val testObserver: Observer<List<Word>> = mock()

        wordDao.getAlphabetizedWords().observeForever(testObserver)
        verify(testObserver).onChanged(kotlin.collections.emptyList())
    }

    @Test
    fun insertWord() = runBlocking{
        val word1 = Word("Test1")
        val word2 = Word("Test2")
        wordDao.insert(word1)
        wordDao.insert(word2)

        val testObserver: Observer<List<Word>> = mock()
        wordDao.getAlphabetizedWords().observeForever(testObserver)

        val listClass = ArrayList::class.java as Class<ArrayList<Word>>
        // ArgumentCaptor to capture the value in onChanged(). Using an arguemntCaptor from Mockito
        // allows you to make more complex assertions on value than equals
        val argumentCaptor = ArgumentCaptor.forClass(listClass)

        verify(testObserver).onChanged(argumentCaptor.capture())
        val capturedArgument = argumentCaptor.value
        assertTrue(capturedArgument.size == 2)
    }

    @Test
    fun getAlphabetizedWords() = runBlocking {
        val word1 = Word("Test1")
        val word2 = Word("Test2")
        wordDao.insert(word1)
        wordDao.insert(word2)

        val testObserver: Observer<List<Word>> = mock()
        wordDao.getAlphabetizedWords().observeForever(testObserver)

        val listClass = ArrayList::class.java as Class<ArrayList<Word>>
        val argumentCaptor = ArgumentCaptor.forClass(listClass)
        verify(testObserver).onChanged(argumentCaptor.capture())
        val capturedArgument = argumentCaptor.value
        assertTrue(capturedArgument.containsAll(listOf(word1, word2)))

    }

    @Test
    fun deleteWord() = runBlocking {
        val word1 = Word("Test1")
        val word2 = Word("Test2")
        wordDao.insert(word1)
        wordDao.insert(word2)

        wordDao.deleteWord(word1)

        val testObserver: Observer<List<Word>> = mock()
        wordDao.getAlphabetizedWords().observeForever(testObserver)

        val listClass = ArrayList::class.java as Class<ArrayList<Word>>
        val argumentCaptor = ArgumentCaptor.forClass(listClass)

        verify(testObserver).onChanged(argumentCaptor.capture())
        assertTrue(argumentCaptor.firstValue.contains(word2))
        assertTrue(argumentCaptor.lastValue.contains(word2))
        assertTrue(argumentCaptor.value.size == 1)
    }

    @Test
    fun deleteAll() = runBlocking {
        val word1 = Word("Test1")
        val word2 = Word("Test2")
        wordDao.insert(word1)
        wordDao.insert(word2)

        wordDao.deleteAll()

        val testObserver: Observer<List<Word>> = mock()
        wordDao.getAlphabetizedWords().observeForever(testObserver)

        val listClass = ArrayList::class.java as Class<ArrayList<Word>>

        verify(testObserver).onChanged(kotlin.collections.emptyList())




    }
}