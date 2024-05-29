package com.example.travelhunter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieDrawable
import com.example.travelhunter.databinding.FragmentSearchFlightsBinding
import com.example.travelhunter.viewmodels.MainViewModel


class SearchFlights : Fragment() {

    private lateinit var binding: FragmentSearchFlightsBinding
    private val vm: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.setBottomNavVisibility(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchFlightsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.playAnimation(binding.flightsNoFlightsImage, 0.0f, 1.0f, 1.0f, LottieDrawable.INFINITE, LottieDrawable.INFINITE)
    }

}