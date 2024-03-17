package com.example.travelhunter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.travelhunter.R
import com.example.travelhunter.databinding.ActivityMainBinding
import com.example.travelhunter.viewmodels.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val nav = supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView) as NavHostFragment
        binding.mainBottomNavigation.setupWithNavController(nav.navController)

        //viewModel.setBottomNavVisibility(false)

        viewModel.get_bottom_nav_visibility.observe(this){
            if (it) binding.mainBottomNavigation.visibility = View.VISIBLE
            else binding.mainBottomNavigation.visibility = View.GONE
        }
    }
}