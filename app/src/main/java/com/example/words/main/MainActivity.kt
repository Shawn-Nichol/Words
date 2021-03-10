package com.example.words.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.words.MyApplication
import com.example.words.R
import com.example.words.main.ui.CustomTouchHelper
import com.example.words.main.ui.RVAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var rvAdapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        submitList()
    }

    private fun initRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        rvAdapter = RVAdapter()

        recyclerView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            val customItemTouchHelper = ItemTouchHelper(CustomTouchHelper(viewModel))
            customItemTouchHelper.attachToRecyclerView(recyclerView)
        }
    }

    private fun submitList() {
        viewModel.wordList.observe(this, {
            it?.let {
                rvAdapter.submitList(it)
            }
        })
    }
}