package com.example.words.main.newword

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.words.MyApplication
import com.example.words.R
import com.example.words.databinding.ActivityNewWordBinding
import com.example.words.databinding.FragmentNewWordBinding
import com.example.words.main.MainViewModel
import com.example.words.newword.NewWordActivity
import com.example.words.room.Word
import javax.inject.Inject


class NewWordFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel

    lateinit var binding: FragmentNewWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_word, container, false)
        binding.binding = this

        return binding.root
    }

    fun saveWord() {
        val editWordView = binding.editWord


        if (!TextUtils.isEmpty(editWordView.text)) {
            val word = Word(editWordView.text.toString())
            viewModel.insertWord(word)
            return
        } else {
            Toast.makeText(context, "No word entered", Toast.LENGTH_SHORT).show()
        }
    }
}