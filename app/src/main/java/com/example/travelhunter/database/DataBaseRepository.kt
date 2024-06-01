package com.example.travelhunter.database

import androidx.lifecycle.LiveData

class DataBaseRepository(private val dataBaseDao: DataBaseDao) {

    val getAllHotels: LiveData<List<HotelEntity>> = dataBaseDao.getAllHotels()

    suspend fun insertHotel(hotelEntity: HotelEntity){
        dataBaseDao.insertHotel(hotelEntity)
    }

    suspend fun deleteAllHotels(){
        dataBaseDao.deleteAllHotels()
    }

}