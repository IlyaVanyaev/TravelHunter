package com.example.travelhunter.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.travelhunter.database.DataBaseRepository
import com.example.travelhunter.database.HotelEntity
import com.example.travelhunter.database.HotelsAndFlightsDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataBaseViewModel(application: Application): AndroidViewModel(application) {

    val getAllHotels: LiveData<List<HotelEntity>>
    private val repository: DataBaseRepository

    init {
        val dataBaseDao = HotelsAndFlightsDataBase.getDataBase(application).dataBaseDao()
        repository = DataBaseRepository(dataBaseDao)
        getAllHotels = repository.getAllHotels
    }

    fun insertHotel(hotelEntity: HotelEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHotel(hotelEntity)
        }
    }

    fun deleteAllHotels(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllHotels()
        }
    }

}