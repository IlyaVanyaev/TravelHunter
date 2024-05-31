package com.example.travelhunter.fragments

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.travelhunter.R
import com.example.travelhunter.databinding.FragmentFlightsBinding
import com.example.travelhunter.interfaces.FlightApi
import com.example.travelhunter.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale


class Flights : Fragment() {

    private lateinit var binding: FragmentFlightsBinding
    private val vm: MainViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFlightsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (vm.getBottomNavVisibility.value == false) vm.setBottomNavVisibility(true)

        binding.flightsToDate.setOnClickListener { datePicker(binding.flightsToDateText) }
        binding.flightsFromDate.setOnClickListener { datePicker(binding.flightsFromDateText) }

        binding.flightsButton.setOnClickListener {

            if(binding.flightsFromEdit.text.isNotEmpty() && binding.flightsToEdit.text.isNotEmpty() && (binding.flightsToDateText.text.isNotEmpty() || binding.flightsFromDateText.text.isNotEmpty())){

                val query = vm.setRoute(binding.flightsFromEdit.text.toString(), binding.flightsToEdit.text.toString())
                vm.getIata(query, true)

                Navigation.findNavController(view).navigate(R.id.action_flights_to_searchFlights)
            }
            else if (binding.flightsFromEdit.text.isNotEmpty() && binding.flightsToEdit.text.isNotEmpty() && (binding.flightsToDateText.text.isEmpty() || binding.flightsFromDateText.text.isEmpty())){
                val query = vm.setRoute(binding.flightsFromEdit.text.toString(), binding.flightsToEdit.text.toString())
                vm.getIata(query, false)
                Navigation.findNavController(view).navigate(R.id.action_flights_to_searchFlights)
            }
            else {
                Toast.makeText(activity, "block", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun datePicker(textView: TextView) {
        val datePicker = context?.let {
            DatePickerDialog(
                it, { DatePicker, year:Int, month:Int, day:Int ->

                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, day)
                    val dateFormat = android.icu.text.SimpleDateFormat("EEEE, dd/MM/yyyy", Locale.getDefault())
                    val formattedDate = dateFormat.format(selectedDate.time)
                    textView.text = formattedDate
            },
                Calendar.getInstance().get(java.util.Calendar.YEAR),
                Calendar.getInstance().get(java.util.Calendar.MONTH),
                Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH)
            )

        }
        datePicker?.show()
    }


}