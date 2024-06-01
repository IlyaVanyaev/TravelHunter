package com.example.travelhunter.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelhunter.R
import com.example.travelhunter.data.SavedItem
import com.example.travelhunter.databinding.HotelItemBinding

class SavedAdapter: ListAdapter<SavedItem, SavedAdapter.ViewHolder>(Comparator()) {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = HotelItemBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(savedItem: SavedItem) = with(binding){


            if (savedItem.label == null){
                hotelLabel.text = "${savedItem.origin}-${savedItem.destination}"
                hotelLocation.text = "${savedItem.departDate}-${savedItem.returnDate}"
                hotelScoreTotal.text = savedItem.price.toString()
            }
            else{
                hotelLabel.text = savedItem.label
                hotelLocation.text = savedItem.locationName
                hotelScoreTotal.text = savedItem.scoreT.toString() + "\u20BD"
                if(savedItem.scoreT!! >= 100000) hotelScoreNight.text = savedItem.scoreT.div(60).toString() + "\u20BD за ночь"
                else hotelScoreNight.text = savedItem.scoreT.div(30).toString() + "\u20BD за ночь"
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hotel_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class Comparator: DiffUtil.ItemCallback<SavedItem>(){
        override fun areItemsTheSame(oldItem: SavedItem, newItem: SavedItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SavedItem, newItem: SavedItem): Boolean {
            return oldItem == newItem
        }
    }

}