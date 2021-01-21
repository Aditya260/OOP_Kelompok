package com.UAS.oop.Database

import androidx.room.*
import com.UAS.opp.Database.Pembeli

@Dao
interface PembeliDao {
    @Insert
    suspend fun addPembeli(pembeli: Pembeli)

    @Update
    suspend fun updatePembeli(pembeli: Pembeli)

    @Delete
    suspend fun deletePembeli(pembeli: Pembeli)

    @Query("SELECT * FROM Pembeli")
    suspend fun getAllPembeli(): List<Pembeli>

    @Query("SELECT * FROM Pembeli WHERE id=:pembeli_id")
    suspend fun getPembeli(pembeli_id: Int) : List<Pembeli>
}