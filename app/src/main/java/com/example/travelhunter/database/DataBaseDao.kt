package com.example.travelhunter.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataBaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotel(hotelEntity: HotelEntity)

    @Query("SELECT * FROM Hotels")
    fun getAllHotels(): LiveData<List<HotelEntity>>

    @Query("DELETE FROM Hotels")
    suspend fun deleteAllHotels()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlight(flightEntity: FlightEntity)

    @Query("SELECT * FROM Flights")
    fun getAllFlights(): LiveData<List<FlightEntity>>

    @Query("DELETE FROM Flights")
    suspend fun deleteAllFlights()

}