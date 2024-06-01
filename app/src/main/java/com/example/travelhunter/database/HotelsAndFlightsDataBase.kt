package com.example.travelhunter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.travelhunter.data.Constants

@Database(entities = [HotelEntity::class, FlightEntity::class], version = 1, exportSchema = false)
abstract class HotelsAndFlightsDataBase: RoomDatabase() {

    abstract fun dataBaseDao(): DataBaseDao

    companion object{
        private var INSTANCE: HotelsAndFlightsDataBase? = null

        fun getDataBase(context: Context): HotelsAndFlightsDataBase{
            val instance = INSTANCE
            if (instance != null) return instance

            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, HotelsAndFlightsDataBase::class.java, Constants.DATABASE_NAME).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}