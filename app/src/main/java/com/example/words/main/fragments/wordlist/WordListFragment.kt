package com.example.words.main.fragments.wordlist


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.words.MyApplication
import com.example.words.R
import com.example.words.databinding.FragmentWordListBinding
import com.example.words.main.MainFragmentFactory
import com.example.words.main.MainViewModel
import com.example.words.main.fragments.wordlist.ui.CustomTouchHelper
import com.example.words.main.fragments.wordlist.ui.RVAdapter
import javax.inject.Inject


class WordListFragment(private val viewModel: MainViewModel) : Fragment() {

    @Inject
    lateinit var rvAdapter: RVAdapter

    lateinit var binding: FragmentWordListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as MyApplication).appComponent.inject(this)
        requireActivity().supportFragmentManager.fragmentFactory = MainFragmentFactory(viewModel)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_word_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.binding = this

        initRecyclerView()
        submitList()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.word_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete_all -> {
                findNavController().navigate(R.id.action_dest_wordListFragment_to_deleteListDialog)
                true
            }
            R.id.menu_restore_list -> {
                findNavController().navigate(R.id.action_dest_wordListFragment_to_restoreWordListDialog)
                true
            }
            R.id.menu_dark_mode -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView() {
        val recyclerView: RecyclerView = binding.rvContainer
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvAdapter = RVAdapter()

        recyclerView.apply {
            adapter = rvAdapter
            this.layoutManager = layoutManager



            val customItemTouchHelper = ItemTouchHelper(CustomTouchHelper(viewModel))
            customItemTouchHelper.attachToRecyclerView(recyclerView)

            // Delay Transition on back press.
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }
    }

    /**
     * submitList, submits the list of words to the adapter so the DiffUtils can load the items.
     */
    private fun submitList() {
        viewModel.wordList.observe(viewLifecycleOwner, {
            it?.let {
                rvAdapter.submitList(it)
            }
        })
    }

    /**
     * Used to create a new Word.
     */
    fun fabNewWord() {
        val action = R.id.action_wordListFragment_to_newWordFragment
        findNavController().navigate(action)
    }


}