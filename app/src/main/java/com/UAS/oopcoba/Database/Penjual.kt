package com.UAS.oopcoba.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "penjual")
data class Penjual(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nama") val nama: String,
    @ColumnInfo(name = "username") val username: String
)