package com.example.words.main.fragments.wordlist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.words.R
import com.example.words.main.fragments.wordlist.WordListFragmentDirections
import com.example.words.data.room.Word
import javax.inject.Inject

class RVAdapter @Inject constructor() : androidx.recyclerview.widget.ListAdapter<Word,
        RVAdapter.ItemViewHolder>(MyDiffCallback()) {

    class MyDiffCallback : DiffUtil.ItemCallback<Word>() {

        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.word == newItem.word
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }
    }

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tv: TextView = view.findViewById(R.id.textView_word)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.tv.text = item.word

        // Set a unique transition name, so transition knows where to end when back button is pressed.
        holder.tv.transitionName = "transition_word_$position"

        holder.tv.setOnClickListener { view ->
            val extras = FragmentNavigatorExtras(holder.tv to "transition_word")

            val action = WordListFragmentDirections.actionDestWordListFragmentToDetailsFragment(item.word)
            view.findNavController().navigate(action, extras)
        }
    }
}