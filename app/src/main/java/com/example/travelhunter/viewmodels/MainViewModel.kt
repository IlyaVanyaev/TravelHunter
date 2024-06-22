package com.example.travelhunter.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.Uri
import android.text.Editable
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.airbnb.lottie.LottieAnimationView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.travelhunter.data.Constants
import com.example.travelhunter.data.Data
import com.example.travelhunter.data.DateFlight
import com.example.travelhunter.data.FlightModel
import com.example.travelhunter.data.Hotels
import com.example.travelhunter.data.Iata
import com.example.travelhunter.interfaces.FlightApi
import com.example.travelhunter.interfaces.IataApi
import com.squareup.picasso.Picasso
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

    private var flight = MutableLiveData<FlightModel>()
    val getFlight: LiveData<FlightModel> = flight

    private var dateFlightList = MutableLiveData<List<DateFlight>>()
    val getDateFlightList: LiveData<List<DateFlight>> = dateFlightList

    private var flightList = MutableLiveData<List<Data>>()
    val getFlightList: LiveData<List<Data>> = flightList

    private var iata = MutableLiveData<Iata>()
    val getIata: LiveData<Iata> = iata

    private var withDate = MutableLiveData<Boolean>()
    val getWithDate : LiveData<Boolean> = withDate

    private var hotelLabels = MutableLiveData<List<Hotels>>()
    val getHotelLabels: LiveData<List<Hotels>> = hotelLabels

    private var backgroundUri = MutableLiveData<String>()
    val getBackgroundUri: LiveData<String> = backgroundUri

    private var background = MutableLiveData<Int>()
    val getBackground: LiveData<Int> = background

    var departure = MutableLiveData<Editable>()
    var returning = MutableLiveData<Editable>()



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
            .baseUrl("https://api.travelpayouts.com/v2/prices/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        flightApi = retrofit.create(FlightApi::class.java)
        iataApi = retrofit.create(IataApi::class.java)
    }

    fun setBottomNavVisibility(visibility: Boolean){
        bottomNavVisibility.value = visibility
    }

    fun setWithDate(with: Boolean){
        withDate.value = with
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


    fun getIata(query: String, withDate: Boolean, departDate: String, returnDate: String) {
        iataApi.getIATA(query).enqueue(object : Callback<Iata>{
            override fun onResponse(p0: Call<Iata>, p1: Response<Iata>) {
                if (p1.isSuccessful){

                    iata.value = p1.body()

                    if (withDate){

                        dateFlightList.value = null

                        iata.value?.destinationIata?.let{
                            getDateFlight(iata.value!!, Constants.API_KEY, departDate, returnDate)
                        }
                    }
                    else{
                        flightList.value = null

                        iata.value?.destinationIata?.let{getFlightsWithoutDate(iata.value!!)}
                    }

                }
            }

            override fun onFailure(p0: Call<Iata>, p1: Throwable) {
                Log.d("RESPONSE FAILURE BABY", p1.message.toString())
            }

        })
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getFlightsWithoutDate(test: Iata){

        val listOfFlights = ArrayList<Data>()

            flightApi.getFlight(test.originIata.iata, test.destinationIata.iata).enqueue(object : Callback<FlightModel>{
                override fun onResponse(p0: Call<FlightModel>, p1: Response<FlightModel>) {
                    if (p1.isSuccessful){
                        Log.d("FLIGHT RESPONSE BABY", p1.body().toString())

                        flight.value = p1.body()
                        for (i in 0 until flight.value!!.data.size){
                            listOfFlights.add(flight.value!!.data[i])
                        }
                        flightList.value = listOfFlights
                    }
                }

                override fun onFailure(p0: Call<FlightModel>, p1: Throwable) {
                    Log.d("RESPONSE FAILURE BABY", p1.message.toString())
                }

            })

    }


    fun getDateFlight(test: Iata, apiKey: String, departDate: String, returnDate: String){
        val url = "https://api.travelpayouts.com/v1/prices/cheap?&depart_date=$departDate&return_date=$returnDate&page=10&origin=${test.originIata.iata}&destination=${test.destinationIata.iata}"
        val queue = Volley.newRequestQueue(getApplication())
        val request =  object: StringRequest(
            Request.Method.GET, url,
            { response ->

                Log.d("RESPONSE BABY", response.toString())
                parseDateFlight(response, test)

            },
            {error ->
                Log.d("Error response", error.toString())
                Toast.makeText(getApplication(), "Response error", Toast.LENGTH_SHORT).show()
            }){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["x-access-token"] = apiKey
                return headers
            }


        }
        queue.add(request)
    }

    private fun parseDateFlight(response: String, test: Iata){
        val flightList = ArrayList<DateFlight>()
        val json = JSONObject(response)
        val dateFlight = DateFlight(
            json.getJSONObject("data").getJSONObject(iata.value?.destinationIata!!.iata).getJSONObject("0").getString("departure_at"),
            json.getJSONObject("data").getJSONObject(iata.value?.destinationIata!!.iata).getJSONObject("0").getString("return_at"),
            json.getJSONObject("data").getJSONObject(iata.value?.destinationIata!!.iata).getJSONObject("0").getInt("price"),
            test.originIata.iata,
            test.destinationIata.iata
        )
        flightList.add(dateFlight)
        dateFlightList.value = flightList

    }

    fun getHotels(city: String){
        hotelLabels.value = null
        val url = "https://engine.hotellook.com/api/v2/lookup.json?query=$city&lang=ru&lookFor=hotel&limit=20&token=${Constants.API_KEY}"
        val queue = Volley.newRequestQueue(getApplication())
        val request =  object: StringRequest(
            Request.Method.GET, url,
            { response ->

                Log.d("RESPONSE BABY", response.toString())
                parseHotels(response)

            },
            {error ->
                Log.d("Error response", error.toString())
                Toast.makeText(getApplication(), "Response error", Toast.LENGTH_SHORT).show()
            }){

        }
        queue.add(request)
    }

    private fun parseHotels(response: String){
        val hotelList = ArrayList<Hotels>()
        val json = JSONObject(response)
        val hotels = json.getJSONObject("results").getJSONArray("hotels")
        for (i in 0 until hotels.length()){
            val hotel = hotels[i] as JSONObject
            val hotelModel = Hotels(
                hotel.getString("label"),
                hotel.getString("locationName"),
                hotel.getInt("_score"),

            )
            hotelList.add(hotelModel)
        }

        hotelLabels.value = hotelList
    }


    fun downloadImage(url: String, target: ImageView){
        Picasso.get().load(url).into(target)
    }


    fun setBackground(uri: String){
        backgroundUri.value = uri
    }

    fun setDefaultBackGround(uri: Int){
        background.value = uri
    }


    fun getTheme(): Boolean{
        val sharedPreferences = getApplication<Application>().getSharedPreferences("Theme", Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean("isDarkTheme", false)
    }

    fun saveTheme(isDarkTheme: Boolean){
        val sharedPreferences = getApplication<Application>().getSharedPreferences("Theme", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putBoolean("isDarkTheme", isDarkTheme)
        editor?.apply()
    }


}