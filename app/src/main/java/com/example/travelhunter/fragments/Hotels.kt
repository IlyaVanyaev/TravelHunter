package com.example.travelhunter.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

        binding.hotelsButton.setOnClickListener {

            if (binding.hotelsCityEdit.text.isNotEmpty()){

                //vm.getHotels(binding.hotelsCityEdit.text.toString())
                vm.setCity(binding.hotelsCityEdit.text.toString())

                Navigation.findNavController(view).navigate(R.id.action_hotels_to_searchHotels)
            }

        }

        binding.hotelsClearCity.setOnClickListener {
            binding.hotelsCityEdit.text.clear()
        }

        binding.hotelsCityEdit.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let {
                    if (it.isNotEmpty()){
                        binding.hotelsClearCity.visibility = View.VISIBLE
                    }
                    else {
                        binding.hotelsClearCity.visibility = View.GONE
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }


}