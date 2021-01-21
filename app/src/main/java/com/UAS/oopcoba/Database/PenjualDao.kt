package com.UAS.oopcoba.Database

import androidx.room.*

@Dao
interface PenjualDao {
    @Insert
    suspend fun addPenjual(penjual: Penjual)

    @Update
    suspend fun updatePenjual(penjual: Penjual)

    @Delete
    suspend fun deletePenjual(penjual: Penjual)

    @Query("SELECT * FROM penjual")
    suspend fun getAllPenjual(): List<Penjual>

    @Query("SELECT * FROM penjual WHERE id=:penjual_id")
    suspend fun getPenjual(penjual_id: Int) : List<Penjual>
}