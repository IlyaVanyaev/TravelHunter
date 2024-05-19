package com.example.travelhunter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.travelhunter.R
import com.example.travelhunter.databinding.FragmentHotelsBinding
import com.example.travelhunter.viewmodels.MainViewModel


class Hotels : Fragment() {

    private lateinit var binding: FragmentHotelsBinding
    private val vm: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHotelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (vm.getBottomNavVisibility.value == false) vm.setBottomNavVisibility(true)
        if (vm.getCustomBack.value == false) vm.enableCustomBack(true)

        binding.hotelsButton.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_hotels_to_searchHotels) }
    }


}