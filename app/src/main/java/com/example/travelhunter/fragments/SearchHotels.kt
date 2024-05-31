package com.example.travelhunter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.example.travelhunter.adapters.FlightListAdapter
import com.example.travelhunter.adapters.FlightsWithDateAdapter
import com.example.travelhunter.adapters.HotelsAdapter
import com.example.travelhunter.databinding.FragmentSearchHotelsBinding
import com.example.travelhunter.viewmodels.MainViewModel


class SearchHotels : Fragment() {

    private lateinit var binding: FragmentSearchHotelsBinding
    private val vm: MainViewModel by activityViewModels()

    private lateinit var adapter: HotelsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        vm.getHotelLabels.observe(viewLifecycleOwner){
            binding.emptyHotels.visibility = View.GONE
            if (it == null) binding.emptyHotels.visibility = View.VISIBLE
            adapter.submitList(it)
        }

        vm.playAnimation(binding.hotelsNoHotelsImage, 0.0f, 1.0f, 1.0f, LottieDrawable.INFINITE, LottieDrawable.INFINITE)
    }

    private fun setRecyclerView() = with(binding) {
        hotelsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = HotelsAdapter()
        hotelsRecyclerView.adapter = adapter
    }

}