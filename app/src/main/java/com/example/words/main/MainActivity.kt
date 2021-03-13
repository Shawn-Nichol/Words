package com.example.words.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.words.MyApplication
import com.example.words.R
import com.example.words.databinding.ActivityMainBinding
import com.example.words.room.WordDao
import com.example.words.room.insertDBWords
import kotlinx.coroutines.launch
import javax.inject.Inject

var TAG = "MyTest"

class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var wordDao: WordDao


    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.binding = this

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete_all -> {
                viewModel.deleteAllWords()
                true
            }
            R.id.menu_restore_list -> {
                lifecycleScope.launch {
                    insertDBWords(wordDao).insert()
                }
                true
            }
            R.id.menu_dark_mode -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}