package com.example.travelhunter.database

import androidx.lifecycle.LiveData

class DataBaseRepository(private val dataBaseDao: DataBaseDao) {

    val getAllHotels: LiveData<List<HotelEntity>> = dataBaseDao.getAllHotels()
    val getAllFlights: LiveData<List<FlightEntity>> = dataBaseDao.getAllFlights()

    suspend fun insertHotel(hotelEntity: HotelEntity){
        dataBaseDao.insertHotel(hotelEntity)
    }

    suspend fun deleteAllHotels(){
        dataBaseDao.deleteAllHotels()
    }

    suspend fun insertFlight(flightEntity: FlightEntity){
        dataBaseDao.insertFlight(flightEntity)
    }

    suspend fun deleteAllFlights(){
        dataBaseDao.deleteAllFlights()
    }

}