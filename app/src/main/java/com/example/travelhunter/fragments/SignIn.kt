package com.example.travelhunter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.travelhunter.R
import com.example.travelhunter.databinding.FragmentSignInBinding
import com.example.travelhunter.viewmodels.MainViewModel


class SignIn : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val vm: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignInBinding.inflate(inflater, container, false)

        vm.setBottomNavVisibility(false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.signInRegister.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_signIn_to_register) }

        binding.signInButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signIn_to_saved)
            vm.setBottomNavVisibility(true)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignIn()
    }
}