package com.example.words.main.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.lang.ClassCastException
import java.lang.IllegalStateException

class RestoreWordListDialog : DialogFragment() {

    internal lateinit var listener: RestoreDialogListener

    interface RestoreDialogListener {
        fun restoreWordList(dialog: DialogFragment)
        fun dontRestoreWordList(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as RestoreDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement RestoreWordListDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle("Restore List")
                .setMessage("This action will restore all the default words")
                .setPositiveButton("YES",
                    DialogInterface.OnClickListener { _, _ ->
                        listener.restoreWordList(this)
                    })
                .setNegativeButton(
                    "NO",
                    DialogInterface.OnClickListener { _, _ ->
                        listener.dontRestoreWordList(this)
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}