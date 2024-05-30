package com.example.travelhunter.data

import com.google.gson.annotations.SerializedName

data class FlightModel(
    val data: Data
)

data class Data(
    @SerializedName("AER")
    val destination: Destination
)

data class Destination(
    @SerializedName("0")
    val flight: Flight
)

data class Flight(
    @SerializedName("departure_at")
    val departureAt: String,
    @SerializedName("return_at")
    val returnAt: String,
    var price: Int
)
