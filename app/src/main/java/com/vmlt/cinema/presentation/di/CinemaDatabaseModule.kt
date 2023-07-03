package com.vmlt.cinema.presentation.di

import android.app.Application
import com.vmlt.cinema.data.db.CinemaDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CinemaDatabaseModule {
    @Provides
    @Singleton
    internal fun providesDatabase(application: Application): CinemaDatabase {
        return CinemaDatabase.getDatabase(application)
    }

}