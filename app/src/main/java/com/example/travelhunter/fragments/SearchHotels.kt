package com.example.travelhunter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.example.travelhunter.adapters.FlightListAdapter
import com.example.travelhunter.adapters.FlightsWithDateAdapter
import com.example.travelhunter.adapters.HotelsAdapter
import com.example.travelhunter.data.Hotels
import com.example.travelhunter.database.HotelEntity
import com.example.travelhunter.databinding.FragmentSearchHotelsBinding
import com.example.travelhunter.interfaces.HotelListener
import com.example.travelhunter.viewmodels.DataBaseViewModel
import com.example.travelhunter.viewmodels.MainViewModel


class SearchHotels : Fragment(), HotelListener {

    private lateinit var binding: FragmentSearchHotelsBinding
    private val vm: MainViewModel by activityViewModels()

    private lateinit var adapter: HotelsAdapter

    private lateinit var dbvm: DataBaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbvm = ViewModelProvider(this)[DataBaseViewModel::class.java]

        vm.setBottomNavVisibility(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchHotelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setRecyclerView()

        binding.hotelsNoHotelsImage.setOnClickListener {
            vm.getHotels(vm.getCity.value!!)
        }

        vm.getHotelLabels.observe(viewLifecycleOwner){
            binding.emptyHotels.visibility = View.GONE
            if (it == null) binding.emptyHotels.visibility = View.VISIBLE
            adapter.submitList(it)
        }

        vm.playAnimation(binding.hotelsNoHotelsImage, 0.0f, 1.0f, 1.0f, LottieDrawable.INFINITE, LottieDrawable.INFINITE)
    }

    private fun setRecyclerView()  {
        binding.hotelsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = HotelsAdapter(this)
        binding.hotelsRecyclerView.adapter = adapter
    }

    override fun onHotelClick(hotels: Hotels) {
        Toast.makeText(activity, "сохранил ${hotels.label}", Toast.LENGTH_SHORT).show()
        dbvm.insertHotel(HotelEntity(null, hotels.label, hotels.locationName, hotels._socre, hotels._socre.div(60)))
    }



}