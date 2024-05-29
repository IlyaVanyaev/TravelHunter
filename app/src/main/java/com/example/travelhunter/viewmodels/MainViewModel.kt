package com.example.travelhunter.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.airbnb.lottie.LottieAnimationView
import com.example.travelhunter.data.Flight
import com.example.travelhunter.interfaces.FlightApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel (application: Application) : AndroidViewModel(application) {

    private var bottomNavVisibility = MutableLiveData<Boolean>()
    val getBottomNavVisibility : LiveData<Boolean> = bottomNavVisibility

    private var flightWithoutDate = MutableLiveData<Flight>()
    val getFlightWithoutDate: LiveData<Flight> = flightWithoutDate


    private var interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
    private var client: OkHttpClient
    private var retrofit: Retrofit
    private var flightApi: FlightApi
    //private  lateinit var flight: Flight


    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.travelpayouts.com/v1/prices/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        flightApi = retrofit.create(FlightApi::class.java)
    }

    fun setBottomNavVisibility(visibility: Boolean){
        bottomNavVisibility.value = visibility
    }


    fun playAnimation(icon: LottieAnimationView, min:Float, max: Float, speed: Float, repeat: Int, mode:Int){
        icon.setMinAndMaxProgress(min, max)
        icon.repeatCount = repeat
        icon.repeatMode= mode
        icon.speed = speed
        icon.playAnimation()
    }


    fun setupRetrofit(){



    }

    fun getFlightsWithoutDate(){
        CoroutineScope(Dispatchers.IO).launch{
            val flight = flightApi.getFlight()
            runBlocking(Dispatchers.Main) {
                flightWithoutDate.value = flight
            }
        }

    }

}