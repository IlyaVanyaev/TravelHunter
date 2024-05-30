package com.example.travelhunter.interfaces

import com.example.travelhunter.data.FlightModel
import com.example.travelhunter.data.Iata
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface FlightApi {
    @Headers("x-access-token: apiKey")
    @GET("cheap?&depart_date=2024-06-03&return_date=2024-06-05&page=10")
    fun getFlight(@Query("origin") origin: String, @Query("destination") destination: String): Call<FlightModel>


}