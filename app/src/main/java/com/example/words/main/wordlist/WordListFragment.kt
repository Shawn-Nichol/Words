package com.example.words.main.wordlist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.words.MyApplication
import com.example.words.R
import com.example.words.databinding.FragmentWordListBinding
import com.example.words.main.MainViewModel
import com.example.words.main.wordlist.ui.CustomTouchHelper
import com.example.words.main.wordlist.ui.RVAdapter
import javax.inject.Inject


class WordListFragment : Fragment() {



    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var rvAdapter: RVAdapter

    lateinit var binding: FragmentWordListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_word_list, container, false)
        binding.binding = this

        initRecyclerView()
        submitList()

        return binding.root
    }

    private fun initRecyclerView() {
        val recyclerView: RecyclerView = binding.recyclerView
        rvAdapter = RVAdapter()

        recyclerView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            val customItemTouchHelper = ItemTouchHelper(CustomTouchHelper(viewModel))
            customItemTouchHelper.attachToRecyclerView(recyclerView)
        }
    }

    private fun submitList() {
        viewModel.wordList.observe(viewLifecycleOwner, {
            it?.let {
                rvAdapter.submitList(it)
            }
        })
    }

    fun fabNewWord() {

    }
}