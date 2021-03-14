package com.example.words.main.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.words.main.TAG
import java.lang.IllegalStateException

class DeleteListDialog : DialogFragment() {


    internal lateinit var listener: DeleteListDialogListener

    interface DeleteListDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host.
            listener = context as DeleteListDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw
            throw ClassCastException("$context must implement DeleteListDialogListener")
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle("Delete All Words")
                .setMessage("Are you sure you want to remove all Words")
                .setPositiveButton("YES",
                    DialogInterface.OnClickListener { _, _ ->
                        Log.i(TAG, "onCreateDialog, postive button")
                        listener.onDialogPositiveClick(this)
                    })
                .setNegativeButton("No",
                DialogInterface.OnClickListener{ _, _ ->
                    listener.onDialogNegativeClick(this)
                })
            builder.create()
        } ?: throw IllegalStateException("Activity Cannot be null")
    }
}