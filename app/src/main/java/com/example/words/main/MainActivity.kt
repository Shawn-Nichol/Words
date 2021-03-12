package com.example.words.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.words.MyApplication
import com.example.words.newword.NewWordActivity
import com.example.words.R
import com.example.words.databinding.ActivityMainBinding
import com.example.words.main.ui.CustomTouchHelper
import com.example.words.main.ui.RVAdapter
import com.example.words.room.Word
import javax.inject.Inject

var TAG = "MyTest"

class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1

    @Inject
    lateinit var viewModel: MainViewModel


    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.binding = this

    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
//        super.onActivityResult(requestCode, resultCode, intentData)
//
//        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
//            intentData?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { reply ->
//                val word = Word(reply)
//                    viewModel.insertWord(word)
//            }
//        } else {
//            Toast.makeText(
//                applicationContext,
//                R.string.empty_not_saved,
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }

}