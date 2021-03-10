package com.example.words.main.ui

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.words.main.MainViewModel
import com.example.words.room.Word

class CustomTouchHelper(val viewModel: MainViewModel) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT

        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val startPosition = viewHolder.adapterPosition
        val endPosition  = target.adapterPosition
        recyclerView.adapter?.notifyItemMoved(startPosition, endPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val editList: MutableList<Word>? = viewModel.wordList.value as MutableList<Word>?
        val editWordList = editList?.toMutableList()

        val word: Word = editWordList!![viewHolder.adapterPosition]
        viewModel.deleteWord(word)
    }

}