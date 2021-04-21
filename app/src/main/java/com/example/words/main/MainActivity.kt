package com.example.words.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.example.words.MyApplication
import com.example.words.R
import com.example.words.databinding.ActivityMainBinding
import com.example.words.main.dialogs.DeleteListDialog
import com.example.words.main.dialogs.RestoreWordListDialog
import com.example.words.room.WordDao
import com.example.words.room.insertDBWords
import kotlinx.coroutines.launch
import javax.inject.Inject

var TAG = "MyTest"

class MainActivity : AppCompatActivity(),
    DeleteListDialog.DeleteListDialogListener,
    RestoreWordListDialog.RestoreDialogListener {



    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var wordDao: WordDao


    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        supportFragmentManager.fragmentFactory = MainFragmentFactory(viewModel)

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.binding = this
        ViewModelProvider(this).get(MainViewModel::class.java)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        val navHostFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment!!.findNavController()
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete_all -> {
                val dialog = DeleteListDialog()
                dialog.show(supportFragmentManager, "Delete List")
                true
            }
            R.id.menu_restore_list -> {
                val dialog = RestoreWordListDialog()
                dialog.show(supportFragmentManager, "Restore Word List")
                true
            }
            R.id.menu_dark_mode -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        viewModel.deleteAllWords()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Toast.makeText(this, "Delete all words canceled", Toast.LENGTH_SHORT).show()
    }

    override fun restoreWordList(dialog: DialogFragment) {
        lifecycleScope.launch {
            insertDBWords(wordDao).insert()
        }
    }

    override fun dontRestoreWordList(dialog: DialogFragment) {
        Toast.makeText(this, "Don not restore original word list.", Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }


}