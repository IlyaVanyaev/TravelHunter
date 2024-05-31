package com.example.travelhunter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.example.travelhunter.adapters.FlightListAdapter
import com.example.travelhunter.adapters.FlightsWithDateAdapter
import com.example.travelhunter.databinding.FragmentSearchFlightsBinding
import com.example.travelhunter.viewmodels.MainViewModel


class SearchFlights : Fragment() {

    private lateinit var binding: FragmentSearchFlightsBinding
    private val vm: MainViewModel by activityViewModels()

    private lateinit var adapter: FlightListAdapter
    private lateinit var dateAdapter: FlightsWithDateAdapter

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

        setRecyclerView()

        vm.getFlightList.observe(viewLifecycleOwner){
            binding.emptyFlights.visibility = View.GONE
            if (it == null) binding.emptyFlights.visibility = View.VISIBLE

            adapter.submitList(it)
        }

        vm.getDateFlightList.observe(viewLifecycleOwner){
            binding.emptyFlights.visibility = View.GONE
            if (it == null) binding.emptyFlights.visibility = View.VISIBLE

            dateAdapter.submitList(it)
        }


    }

    private fun setRecyclerView() = with(binding) {
        flightsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = FlightListAdapter()
        dateAdapter = FlightsWithDateAdapter()

        vm.getWithDate.observe(viewLifecycleOwner){
            if (it) flightsRecyclerView.adapter = dateAdapter
            else flightsRecyclerView.adapter = adapter
        }
    }

}