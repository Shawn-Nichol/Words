package com.example.words.newword

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.words.R
import com.example.words.databinding.ActivityNewWordBinding

class NewWordActivity : AppCompatActivity() {


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_word)
        binding.binding = this
        editWordView = binding.editWord

    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }


}