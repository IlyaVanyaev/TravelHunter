package com.example.travelhunter.data

data class Flight(
    val destination: String,
    val departureAt: String,
    val returnAt: String,
    var price: Int
)
