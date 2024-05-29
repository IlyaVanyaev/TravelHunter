package com.example.travelhunter.interfaces

import com.example.travelhunter.data.Flight
import retrofit2.http.GET
import retrofit2.http.Headers

interface FlightApi {
    @Headers("x-access-token: apiKey")
    @GET("cheap?origin=MOW&destination=AER&depart_date=2024-06-03&return_date=2024-06-05&page=10")
    suspend fun getFlight(): Flight
}