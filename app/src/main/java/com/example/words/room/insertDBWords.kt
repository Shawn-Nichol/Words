package com.example.words.room

import android.content.Context
import javax.inject.Inject

class insertDBWords @Inject constructor (val wordDao: WordDao) {


    suspend fun insert() {
        // Add sample words.
        var word = Word("Hello")
        wordDao.insert(word)
        word = Word("World!")
        wordDao.insert(word)
        word = Word("Word 02")
        wordDao.insert(word)
        word = Word("Word 03")
        wordDao.insert(word)
        word = Word("Word 04")
        wordDao.insert(word)
        word = Word("Word 05")
        wordDao.insert(word)
        word = Word("Word 06")
        wordDao.insert(word)
        word = Word("Word 07")
        wordDao.insert(word)
        word = Word("Word 08")
        wordDao.insert(word)
        word = Word("Word 09")
        wordDao.insert(word)
        word = Word("Word 10")
        wordDao.insert(word)
        word = Word("Word 11")
        wordDao.insert(word)
        word = Word("Word 12")
        wordDao.insert(word)
        word = Word("Word 13")
        wordDao.insert(word)
        word = Word("Word 14")
        wordDao.insert(word)
        word = Word("Word 15")
        wordDao.insert(word)
        word = Word("Word 16")
        wordDao.insert(word)
        word = Word("Word 17")
        wordDao.insert(word)
        word = Word("Word 18")
        wordDao.insert(word)
        word = Word("Word 19")
        wordDao.insert(word)
        word = Word("Word 20")
        wordDao.insert(word)
        word = Word("Word 21")
        wordDao.insert(word)
        word = Word("Word 22")
        wordDao.insert(word)
        word = Word("Word 23")
        wordDao.insert(word)
        word = Word("Word 24")
        wordDao.insert(word)
        word = Word("Word 25")
        wordDao.insert(word)
        word = Word("Word 26")
        wordDao.insert(word)
        word = Word("Word 27")
        wordDao.insert(word)
        word = Word("Word 28")
        wordDao.insert(word)
        word = Word("Word 29")
        wordDao.insert(word)
        word = Word("Word 30")
        wordDao.insert(word)
        word = Word("Word 31")
        wordDao.insert(word)
        word = Word("Word 32")
        wordDao.insert(word)
        word = Word("Word 33")
        wordDao.insert(word)
        word = Word("Word 34")
        wordDao.insert(word)
        word = Word("Word 35")
        wordDao.insert(word)
        word = Word("Word 36")
        wordDao.insert(word)
        word = Word("Word 37")
        wordDao.insert(word)
        word = Word("Word 38")
        wordDao.insert(word)
        word = Word("Word 39")
        wordDao.insert(word)
        word = Word("Word 40")
        wordDao.insert(word)
        word = Word("Word 41")
        wordDao.insert(word)
        word = Word("Word 42")
        wordDao.insert(word)
        word = Word("Word 43")
        wordDao.insert(word)
        word = Word("Word 44")
        wordDao.insert(word)
        word = Word("Word 45")
        wordDao.insert(word)
        word = Word("Word 46")
        wordDao.insert(word)
        word = Word("Word 47")
        wordDao.insert(word)
        word = Word("Word 48")
        wordDao.insert(word)
        word = Word("Word 49")
        wordDao.insert(word)
    }
}

