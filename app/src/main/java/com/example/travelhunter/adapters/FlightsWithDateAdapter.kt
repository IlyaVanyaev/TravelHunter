package com.example.travelhunter.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelhunter.R
import com.example.travelhunter.data.FlightModel
import com.example.travelhunter.databinding.FlightItemBinding

class FlightsWithDateAdapter: ListAdapter<FlightModel, FlightsWithDateAdapter.ViewHolder>(Comparator()) {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = FlightItemBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(flightModel: FlightModel) = with(binding){

            flightPrice.text = flightModel.data.destination.flight.price.toString() + "\u20BD"
            flightDepartureDate.text = flightModel.data.destination.flight.departureAt.drop(11).dropLast(9) + "\n" + flightModel.data.destination.flight.departureAt.dropLast(15)
            flightReturnDate.text = flightModel.data.destination.flight.returnAt.drop(11).dropLast(9) + "\n" + flightModel.data.destination.flight.returnAt.dropLast(15)

        }

    }


    class Comparator: DiffUtil.ItemCallback<FlightModel>(){
        override fun areItemsTheSame(oldItem: FlightModel, newItem: FlightModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FlightModel, newItem: FlightModel): Boolean {
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