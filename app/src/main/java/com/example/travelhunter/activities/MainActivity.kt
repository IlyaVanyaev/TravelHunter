package com.example.travelhunter.activities

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.travelhunter.R
import com.example.travelhunter.databinding.ActivityMainBinding
import com.example.travelhunter.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var nav: NavHostFragment


    private val callback = object : OnBackPressedCallback(false){
        override fun handleOnBackPressed() {
            if (binding.mainBottomNavigation.selectedItemId != R.id.saved){
                nav.navController.popBackStack(R.id.saved, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        nav = supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView) as NavHostFragment
        binding.mainBottomNavigation.setupWithNavController(nav.navController)

        //viewModel.setBottomNavVisibility(false)

        viewModel.getBottomNavVisibility.observe(this){
            if (it) binding.mainBottomNavigation.visibility = View.VISIBLE
            else binding.mainBottomNavigation.visibility = View.GONE
        }

        onBackPressedDispatcher.addCallback(this, callback)

        viewModel.getCustomBack.observe(this){ callback.isEnabled = it }


    }

}