package com.example.travelhunter.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.travelhunter.R
import com.example.travelhunter.databinding.FragmentFlightsBinding
import com.example.travelhunter.viewmodels.MainViewModel
import android.icu.util.Calendar
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.travelhunter.activities.MainActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.Year
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale


class Flights : Fragment() {

    private lateinit var binding: FragmentFlightsBinding
    //private val vm: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //vm = ViewModelProvider(this)[MainViewModel::class.java]
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

        binding.flightsToDate.setOnClickListener { datePicker(binding.flightsToDateText) }
        binding.flightsFromDate.setOnClickListener { datePicker(binding.flightsFromDateText) }
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

    companion object {

        @JvmStatic
        fun newInstance() = Flights()
    }
}