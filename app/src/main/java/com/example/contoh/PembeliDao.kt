package com.example.contoh

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PembeliDao {
    @Query("SELECT * FROM pembeli")
    fun getAll(): List<Pembeli>

    @Query("SELECT * FROM pembeli WHERE no IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Pembeli>

    @Query("SELECT * FROM pembeli WHERE nama LIKE :nama AND " +
            "order LIKE :order LIMIT 1")
    fun findByName(nama: String, order: String): Pembeli

    @Insert
    fun insertAll(vararg pembeli: Pembeli)

    @Delete
    fun delete(pembeli: Pembeli)
}