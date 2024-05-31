package com.example.travelhunter.interfaces

import com.example.travelhunter.data.Hotels

interface HotelListener {
    fun onHotelClick(hotels: Hotels)
}