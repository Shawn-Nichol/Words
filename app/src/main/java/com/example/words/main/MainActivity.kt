package com.example.words.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


        setSupportActionBar(findViewById(R.id.my_toolbar))
        val navHostFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment!!.findNavController()
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }


}