package com.example.travelhunter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.example.travelhunter.adapters.SavedAdapter
import com.example.travelhunter.data.SavedItem
import com.example.travelhunter.database.HotelEntity
import com.example.travelhunter.databinding.FragmentSavedBinding
import com.example.travelhunter.viewmodels.DataBaseViewModel
import com.example.travelhunter.viewmodels.MainViewModel


class Saved : Fragment() {

    private val vm: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentSavedBinding

    private lateinit var adapter: SavedAdapter

    private lateinit var dbvm: DataBaseViewModel

    private val savedList = ArrayList<SavedItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbvm = ViewModelProvider(this)[DataBaseViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (vm.getBottomNavVisibility.value == false) vm.setBottomNavVisibility(true)

        setRecyclerView()
        savedList.clear()

        dbvm.getAllHotels.observe(viewLifecycleOwner){

            binding.emptySaves.visibility = View.GONE
            if (it.isEmpty()) binding.emptySaves.visibility = View.VISIBLE

            it?.let{
                it.forEach {it1->
                    savedList.add(SavedItem(it1.label, it1.locationName, it1.scoreTotal, it1.scoreNight, null, null, null, null, null))
                }

                adapter.submitList(savedList)
            }

        }

        dbvm.getAllFlights.observe(viewLifecycleOwner){

            binding.emptySaves.visibility = View.GONE
            if (it.isEmpty()) binding.emptySaves.visibility = View.VISIBLE

            it?.let{
                it.forEach {it1->
                    savedList.add(SavedItem(null, null, null, null, it1.depart, it1.route.dropLast(3), it1.route.drop(4), it1.ret, it1.price))
                }
                adapter.submitList(savedList)
            }
        }

        vm.playAnimation(binding.savedNoSavesImage, 0.0f, 1.0f, 1.0f, LottieDrawable.INFINITE, LottieDrawable.INFINITE)
    }


    private fun setRecyclerView()  {
        binding.savedRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = SavedAdapter()
        binding.savedRecyclerView.adapter = adapter
    }

}