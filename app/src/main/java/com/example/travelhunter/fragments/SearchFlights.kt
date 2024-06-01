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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.example.travelhunter.adapters.FlightListAdapter
import com.example.travelhunter.adapters.FlightsWithDateAdapter
import com.example.travelhunter.data.Data
import com.example.travelhunter.data.DateFlight
import com.example.travelhunter.database.FlightEntity
import com.example.travelhunter.databinding.FragmentSearchFlightsBinding
import com.example.travelhunter.interfaces.DateFlightListener
import com.example.travelhunter.interfaces.FlightListener
import com.example.travelhunter.viewmodels.DataBaseViewModel
import com.example.travelhunter.viewmodels.MainViewModel


class SearchFlights : Fragment(), FlightListener, DateFlightListener {

    private lateinit var binding: FragmentSearchFlightsBinding
    private val vm: MainViewModel by activityViewModels()

    private lateinit var dbvm: DataBaseViewModel

    private lateinit var adapter: FlightListAdapter
    private lateinit var dateAdapter: FlightsWithDateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.setBottomNavVisibility(false)

        dbvm = ViewModelProvider(this)[DataBaseViewModel::class.java]
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

    private fun setRecyclerView() {
        binding.flightsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = FlightListAdapter(this)
        dateAdapter = FlightsWithDateAdapter(this)

        vm.getWithDate.observe(viewLifecycleOwner){
            if (it) binding.flightsRecyclerView.adapter = dateAdapter
            else binding.flightsRecyclerView.adapter = adapter
        }
    }

    override fun onFlightClick(data: Data) {
        Toast.makeText(activity, "сохранил ${data.value}", Toast.LENGTH_SHORT).show()
        dbvm.insertFlight(FlightEntity(null, data.value, data.depart_date, data.return_date, "${data.origin}-${data.destination}"))
    }

    override fun onDateFlightClick(dateFlight: DateFlight) {
        Toast.makeText(activity, "сохранил ${dateFlight.price}", Toast.LENGTH_SHORT).show()
    }

}