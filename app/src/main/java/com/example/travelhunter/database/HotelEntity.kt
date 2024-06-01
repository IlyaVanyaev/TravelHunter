package com.example.travelhunter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hotels")
data class HotelEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "label")
    var label: String,
    @ColumnInfo(name = "location")
    var locationName: String,
    @ColumnInfo(name = "score")
    var scoreTotal: Int,
    @ColumnInfo(name = "score_night")
    var scoreNight: Int,
)
