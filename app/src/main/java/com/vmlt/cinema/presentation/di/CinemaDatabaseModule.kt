package com.vmlt.cinema.presentation.di

import android.app.Application
import androidx.room.Room
import com.vmlt.cinema.data.db.CinemaDatabase
import com.vmlt.cinema.data.db.CinemaDatabase.Companion.DATABASE_NAME
import com.vmlt.cinema.data.db.MovieDao
import com.vmlt.cinema.data.db.SessionDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CinemaDatabaseModule {
    @Provides
    @Singleton
    internal fun providesDatabase(application: Application): CinemaDatabase {
        return Room.databaseBuilder(
            application,
            CinemaDatabase::class.java,
            DATABASE_NAME
        )// TODO: Add future migrations from CinemaDatabase class
        // .addMigrations(...)
        .build()
    }

    @Provides
    internal fun providesMovieDao(cinemaDatabase: CinemaDatabase) : MovieDao {
        return cinemaDatabase.getMovieDao()
    }

    @Provides
    internal fun providesSessionDao(cinemaDatabase: CinemaDatabase) : SessionDao {
        return cinemaDatabase.getSessionDao()
    }
}