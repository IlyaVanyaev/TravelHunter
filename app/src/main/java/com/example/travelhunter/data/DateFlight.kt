package com.example.travelhunter.data

import com.google.gson.annotations.SerializedName

data class DateFlight(
    //@SerializedName("departure_at")
    val departureAt: String,
    //@SerializedName("return_at")
    val returnAt: String,
    var price: Int
)
