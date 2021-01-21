package com.UAS.oopcoba.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.UAS.oop.Database.PembeliDao
import com.UAS.opp.Database.Pembeli

@Database(entities = arrayOf(Pembeli::class, Penjual::class), version = 1)

abstract class AppRoomDB : RoomDatabase() {

    abstract fun pembeliDao(): PembeliDao
    abstract fun PenjualDao(): PenjualDao

    companion object {

        @Volatile
        private var INSTANCE: AppRoomDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
context.applicationContext,
AppRoomDB::class.java,
"APPDB"
).build()

}
}