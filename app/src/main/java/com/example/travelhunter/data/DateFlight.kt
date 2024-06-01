package com.example.travelhunter.data

import com.google.gson.annotations.SerializedName

data class DateFlight(
    val departureAt: String,
    val returnAt: String,
    var price: Int,
    val originIata: String,
    val destIata: String
)
