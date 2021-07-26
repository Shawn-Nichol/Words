package com.example.words.main.fragments.details

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.words.MyApplication
import com.example.words.R
import com.example.words.databinding.FragmentDetailsBinding
import com.example.words.main.MainViewModel


class DetailsFragment(private val viewModel: MainViewModel) : Fragment() {



    lateinit var binding: FragmentDetailsBinding
    var word: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        val args = DetailsFragmentArgs.fromBundle(requireArguments())
        word = args.keyWord

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.binding = this

        binding.tvWordTitle.text = word

        return binding.root
    }
}