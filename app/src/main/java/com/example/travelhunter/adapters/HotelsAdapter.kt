package com.example.travelhunter.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelhunter.R
import com.example.travelhunter.data.Hotels
import com.example.travelhunter.databinding.HotelItemBinding
import com.example.travelhunter.interfaces.HotelListener

class HotelsAdapter(private val listener: HotelListener): ListAdapter<Hotels, HotelsAdapter.ViewHolder>(Comparator()) {


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = HotelItemBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(hotels: Hotels, listener: HotelListener) = with(binding){

            hotelLabel.text = hotels.label
            hotelLocation.text = hotels.locationName
            hotelScoreTotal.text = hotels._socre.toString() + "\u20BD"
            if(hotels._socre >= 100000) hotelScoreNight.text = hotels._socre.div(60).toString() + "\u20BD за ночь"
            else hotelScoreNight.text = hotels._socre.div(30).toString() + "\u20BD за ночь"

            itemView.setOnClickListener {
                listener.onHotelClick(hotels)
            }

        }

    }


    class Comparator: DiffUtil.ItemCallback<Hotels>(){
        override fun areItemsTheSame(oldItem: Hotels, newItem: Hotels): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Hotels, newItem: Hotels): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hotel_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

}