package com.example.travelhunter.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelhunter.R
import com.example.travelhunter.data.DateFlight
import com.example.travelhunter.databinding.FlightItemBinding
import com.example.travelhunter.interfaces.DateFlightListener

class FlightsWithDateAdapter(private val listener: DateFlightListener): ListAdapter<DateFlight, FlightsWithDateAdapter.ViewHolder>(Comparator()) {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = FlightItemBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(dateFlight: DateFlight, listener: DateFlightListener) = with(binding){

            flightPrice.text = dateFlight.price.toString() + "\u20BD"
            flightDepartureDate.text = dateFlight.departureAt.drop(11).dropLast(9) + "\n" + dateFlight.departureAt.dropLast(15)
            flightReturnDate.text = dateFlight.returnAt.drop(11).dropLast(9) + "\n" + dateFlight.returnAt.dropLast(15)

            itemView.setOnClickListener {
                listener.onDateFlightClick(dateFlight)
            }

        }

    }


    class Comparator: DiffUtil.ItemCallback<DateFlight>(){
        override fun areItemsTheSame(oldItem: DateFlight, newItem: DateFlight): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DateFlight, newItem: DateFlight): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.flight_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

}