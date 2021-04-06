package com.example.words.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.words.main.fragments.details.DetailsFragment
import com.example.words.main.fragments.newword.NewWordFragment
import com.example.words.main.fragments.wordlist.WordListFragment

class MainFragmentFactory(private val viewModel: MainViewModel) : FragmentFactory()  {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {

            WordListFragment::class.java.name -> {
                WordListFragment(viewModel)
            }

            DetailsFragment::class.java.name -> {
                DetailsFragment(viewModel)
            }

            NewWordFragment::class.java.name -> {
                NewWordFragment(viewModel)
            }

            else -> super.instantiate(classLoader, className)

        }


    }
}