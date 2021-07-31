package com.example.words.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.example.words.MyApplication
import com.example.words.R
import com.example.words.databinding.ActivityMainBinding
import com.example.words.data.room.WordDao
import com.google.android.material.navigation.NavigationView

import javax.inject.Inject

var TAG = "MyTest"

class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var wordDao: WordDao


    lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration:AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout


    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        supportFragmentManager.fragmentFactory = MainFragmentFactory(viewModel)

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.binding = this

        // Toolbar is the action bar.
        setSupportActionBar(findViewById(R.id.toolbar))


        initNavigation()
        initNavDrawerClickListener()

    }

    override fun onStart() {
        super.onStart()
        changeThemeMode(viewModel.themeMode)

    }

    override fun onPause() {
        super.onPause()
        viewModel.themeModeSave()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val menuItem = menu.findItem(R.id.menu_dark_mode)
        menuItem.setTitle(if (viewModel.themeMode == 1) R.string.dark_mode_off else R.string.dark_mode_on)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_dark_mode -> {

                viewModel.themeMode = if (viewModel.themeMode == 2) 1 else 2

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

    private fun initNavigation() {

        // Get FragmentManager for nav_host_fragment
        val navHostFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

        // Get NavController for NavHostFragment
        navController = navHostFragment!!.findNavController()

        drawerLayout = binding.drawerLayout

        //Customize the UI of the Action bar.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.dest_wordListFragment), drawerLayout)

        // Setup the action bar for use with navController
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun initNavDrawerClickListener() {
        val navView = findViewById<NavigationView>(R.id.nav_drawer_view)
        navView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.nav_first_fragment -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    Toast.makeText(this, "Fragment one", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_second_fragment -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    Toast.makeText(this, "Fragment two", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }



}