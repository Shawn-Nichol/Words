package com.example.words.main.fragments.newword

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.words.MyApplication
import com.example.words.R
import com.example.words.databinding.FragmentNewWordBinding
import com.example.words.main.MainViewModel
import com.example.words.room.Word
import javax.inject.Inject


class NewWordFragment(private val viewModel: MainViewModel) : Fragment() {

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
        val editWordView = binding.etNewWord


        if (!TextUtils.isEmpty(editWordView.text)) {
            val word = Word(editWordView.text.toString())
            viewModel.insertWord(word)

            // Hide keyboard
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)

            findNavController().popBackStack()
        } else {
            Toast.makeText(context, getString(R.string.Toast_no_word), Toast.LENGTH_SHORT).show()
        }
    }

}