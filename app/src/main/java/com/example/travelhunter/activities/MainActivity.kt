package com.example.travelhunter.activities

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        nav = supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView) as NavHostFragment
        binding.mainBottomNavigation.setupWithNavController(nav.navController)


        if (viewModel.getTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            viewModel.setDefaultBackGround(R.drawable.black_amoled)
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            viewModel.setDefaultBackGround(R.drawable.gradient_blue)
        }


        viewModel.getBottomNavVisibility.observe(this){
            if (it) binding.mainBottomNavigation.visibility = View.VISIBLE
            else binding.mainBottomNavigation.visibility = View.GONE
        }

        viewModel.getBackgroundUri.observe(this){
            viewModel.downloadImage(it, binding.mainBackground)
        }

        viewModel.getBackground.observe(this){
            binding.mainBackground.setImageResource(it)
        }

    }

}