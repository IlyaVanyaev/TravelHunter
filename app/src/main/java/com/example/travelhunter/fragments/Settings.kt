package com.example.travelhunter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.travelhunter.R
import com.example.travelhunter.databinding.FragmentSettingsBinding
import com.example.travelhunter.viewmodels.MainViewModel


class Settings : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val vm: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (vm.getBottomNavVisibility.value == false) vm.setBottomNavVisibility(true)

        binding.settingsProfileEditButton.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_settings_to_mainSettings) }

        //binding.settingsLogOutButton.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_settings_to_signIn) }
    }


}