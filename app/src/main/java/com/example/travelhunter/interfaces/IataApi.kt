package com.example.travelhunter.interfaces


import com.example.travelhunter.data.Iata
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IataApi {
    @GET("https://www.travelpayouts.com/widgets_suggest_params?")
    fun getIATA(@Query("q") q: String): Call<Iata>
}