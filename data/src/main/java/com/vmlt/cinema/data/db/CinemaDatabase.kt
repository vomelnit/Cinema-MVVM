package com.vmlt.cinema.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vmlt.cinema.data.entities.MovieEntity
import com.vmlt.cinema.data.entities.SessionEntity

@Database(
    entities = [MovieEntity::class, SessionEntity::class],
    version = 1 // TODO: Increase version if you provide any change to DB
)
abstract class CinemaDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "cinemaDatabase"

        // Migrations
        // A migration object is an object that defines how you take all rows with the old schema
        // and convert them to rows in the new schema, so that no data is lost

        // TODO: Add Migration objects

    }

    abstract fun getMovieDao(): MovieDao
    abstract fun getSessionDao(): SessionDao
}