package com.example.travelhunter.data

import com.google.gson.annotations.SerializedName

data class Iata(
    @SerializedName("origin")
    val originIata: OriginIata,
    @SerializedName("destination")
    val destinationIata: DestinationIata
)

data class OriginIata(
    val iata: String
)

data class DestinationIata(
    val iata: String
)
