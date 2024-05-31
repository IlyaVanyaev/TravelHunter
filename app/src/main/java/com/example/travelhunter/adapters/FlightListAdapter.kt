package com.example.travelhunter.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelhunter.R
import com.example.travelhunter.data.Data
import com.example.travelhunter.data.DateFlight
import com.example.travelhunter.data.FlightModel
import com.example.travelhunter.databinding.FlightItemBinding

class FlightListAdapter: ListAdapter<Data, FlightListAdapter.ViewHolder>(Comparator()) {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = FlightItemBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(data: Data) = with(binding){

            flightPrice.text = data.value.toString() + "\u20BD"
            flightDepartureDate.text = data.depart_date
            flightReturnDate.text = data.return_date
            flightRoute.text = "${data.origin}-${data.destination}"

        }

    }


    class Comparator: DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.flight_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}