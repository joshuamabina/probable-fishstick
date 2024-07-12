package com.example.smartmovie

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class DB : RoomDatabase () {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile private var instance: DB? = null

        fun getDatabase(context: Context): DB =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, DB::class.java, "smartmovie")
                .build()
    }
}
