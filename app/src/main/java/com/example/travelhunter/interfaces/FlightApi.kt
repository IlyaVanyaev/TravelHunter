package com.example.travelhunter.interfaces

import com.example.travelhunter.data.Constants
import com.example.travelhunter.data.FlightModel
import com.example.travelhunter.data.Iata
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface FlightApi {
    @Headers("x-access-token: ${Constants.API_KEY}")
    @GET("latest?currency=rub&page=1&limit=30&show_to_affiliates=true&sorting=price&trip_class=0")
    fun getFlight(@Query("origin") origin: String, @Query("destination") destination: String): Call<FlightModel>


}