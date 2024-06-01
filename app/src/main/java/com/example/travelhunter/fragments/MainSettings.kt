package com.example.travelhunter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieDrawable
import com.example.travelhunter.databinding.ActivityMainBinding
import com.example.travelhunter.databinding.FragmentMainSettingsBinding
import com.example.travelhunter.viewmodels.MainViewModel


class MainSettings : Fragment() {

    private lateinit var binding: FragmentMainSettingsBinding
    private val vm: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.setBottomNavVisibility(false)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.download.setOnClickListener{
            vm.playAnimation(binding.download, 0.0f, 1.0f, 10.0f, 1, LottieDrawable.REVERSE)

            if (binding.searchImage.text.isNotEmpty()){
                vm.setBackground(binding.searchImage.text.toString())
            }
        }

        binding.clear.setOnClickListener {
            binding.searchImage.text.clear()
        }



    }


}