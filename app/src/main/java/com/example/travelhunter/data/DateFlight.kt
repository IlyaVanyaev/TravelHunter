package com.example.travelhunter.data

import com.google.gson.annotations.SerializedName

data class DateFlight(
    val departureAt: String,
    val returnAt: String,
    var price: Int
)
