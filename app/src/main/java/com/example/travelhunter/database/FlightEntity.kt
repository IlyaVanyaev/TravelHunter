package com.example.travelhunter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Flights")
data class FlightEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "price")
    var price: Int,
    @ColumnInfo(name = "departure")
    var depart: String,
    @ColumnInfo(name = "return")
    var ret: String,
    @ColumnInfo(name = "route")
    var route: String,
)
