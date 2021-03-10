package com.example.words.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.words.R
import com.example.words.room.Word
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
    }
}