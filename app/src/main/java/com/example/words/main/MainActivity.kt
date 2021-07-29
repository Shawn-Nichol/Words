package com.example.words.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.words.MyApplication
import com.example.words.R
import com.example.words.databinding.ActivityMainBinding
import com.example.words.data.room.WordDao
import javax.inject.Inject

var TAG = "MyTest"

class MainActivity : AppCompatActivity() {



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


        setSupportActionBar(findViewById(R.id.toolbar))
        val navHostFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment!!.findNavController()
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onStart() {
        super.onStart()
        Log.i("Practice", "onStart themeMode = ${viewModel.themeMode}")
        changeThemeMode(viewModel.themeMode)
    }

    override fun onPause() {
        super.onPause()
        Log.i("Practice", "onPause themeMode = ${viewModel.themeMode}")
        viewModel.themeModeSave()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_dark_mode -> {

                    // Move to MainActivity since it has to be trouble shooted

                    if(viewModel.themeMode == 2) {
                        viewModel.themeMode = 1

                    } else {
                        viewModel.themeMode = 2
                    }
                    Log.i("Practice", "ThemeMode = ${viewModel.themeMode}")
                    changeThemeMode(viewModel.themeMode)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun changeThemeMode(mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}