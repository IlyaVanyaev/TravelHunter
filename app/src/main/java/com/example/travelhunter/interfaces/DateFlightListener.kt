package com.example.travelhunter.interfaces

import com.example.travelhunter.data.DateFlight

interface DateFlightListener {
    fun onDateFlightClick(dateFlight: DateFlight)
}