package com.example.words.main.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.words.R
import com.example.words.main.MainViewModel
import com.google.android.material.snackbar.Snackbar
import java.lang.IllegalStateException

class DeleteListDialog(val viewModel: MainViewModel) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle(getString(R.string.dialog_delete_title))
                .setMessage(getString(R.string.dialog_delete_message))
                .setPositiveButton(getString(R.string.dialog_confirm)) { _, _ ->
                    viewModel.deleteAllWords()

                }
                .setNegativeButton(getString(R.string.dialog_cancel)) { _, _ ->
                    Snackbar.make(
                        requireActivity().findViewById(R.id.WordListFragment),
                        getString(R.string.snackbar_delete_canceled),
                        2000
                    ).show()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity Cannot be null")
    }
}