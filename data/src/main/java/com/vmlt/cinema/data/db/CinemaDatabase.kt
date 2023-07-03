package com.vmlt.cinema.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vmlt.cinema.data.entities.MovieEntity
import com.vmlt.cinema.data.entities.SessionEntity

@Database(entities = [MovieEntity::class, SessionEntity::class], version = 1)
abstract class CinemaDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
    abstract fun getSessionDao(): SessionDao

    companion object {
        @Volatile
        private var Instance: CinemaDatabase? = null

        fun getDatabase(context: Context): CinemaDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CinemaDatabase::class.java, "cinemaDatabase")
                    .build()
                    .also { Instance = it }
            }
        }

    }
}