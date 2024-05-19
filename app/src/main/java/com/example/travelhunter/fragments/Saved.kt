package com.example.travelhunter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieDrawable
import com.example.travelhunter.databinding.FragmentSavedBinding
import com.example.travelhunter.viewmodels.MainViewModel


class Saved : Fragment() {

    private val vm: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentSavedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (vm.getBottomNavVisibility.value == false) vm.setBottomNavVisibility(true)
        if (vm.getCustomBack.value == true) vm.enableCustomBack(false)

        vm.playAnimation(binding.savedNoSavesImage, 0.0f, 1.0f, 1.0f, LottieDrawable.INFINITE, LottieDrawable.INFINITE)
    }


}