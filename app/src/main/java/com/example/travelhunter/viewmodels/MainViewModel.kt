package com.example.travelhunter.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.airbnb.lottie.LottieAnimationView
import com.example.travelhunter.data.FlightModel
import com.example.travelhunter.data.Iata
import com.example.travelhunter.interfaces.FlightApi
import com.example.travelhunter.interfaces.IataApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel (application: Application) : AndroidViewModel(application) {

    private var bottomNavVisibility = MutableLiveData<Boolean>()
    val getBottomNavVisibility : LiveData<Boolean> = bottomNavVisibility

    private var flightWithoutDate = MutableLiveData<FlightModel>()
    val getFlightWithoutDate: LiveData<FlightModel> = flightWithoutDate

    private var iata = MutableLiveData<Iata>()
    val getIata: LiveData<Iata> = iata


    private var interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
    private var client: OkHttpClient
    private var retrofit: Retrofit
    private var flightApi: FlightApi
    private var iataApi: IataApi
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
        iataApi = retrofit.create(IataApi::class.java)
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



    fun setRoute(origin: String, destination: String): String{
        return "$origin $destination"
    }


    fun getIata(query: String, withDate: Boolean) {
        iataApi.getIATA(query).enqueue(object : Callback<Iata>{
            override fun onResponse(p0: Call<Iata>, p1: Response<Iata>) {
                if (p1.isSuccessful){

                    iata.value = p1.body()

                    if (withDate){
                        TODO()
                    }
                    else{
                        iata.value?.destinationIata?.let{
                            getFlightsWithoutDate(iata.value!!)
                        }
                    }

                }
            }

            override fun onFailure(p0: Call<Iata>, p1: Throwable) {
                Log.d("RESPONSE FAILURE BABY", p1.message.toString())
            }

        })
    }

    private fun getFlightsWithoutDate(test: Iata){

            flightApi.getFlight(test.originIata.iata, test.destinationIata.iata).enqueue(object : Callback<FlightModel>{
                override fun onResponse(p0: Call<FlightModel>, p1: Response<FlightModel>) {
                    if (p1.isSuccessful){
                        Log.d("RESPONSE BABY", p1.body().toString())
                        flightWithoutDate.value = p1.body()
                    }
                }

                override fun onFailure(p0: Call<FlightModel>, p1: Throwable) {
                    Log.d("RESPONSE FAILURE BABY", p1.message.toString())
                }

            })

    }


}