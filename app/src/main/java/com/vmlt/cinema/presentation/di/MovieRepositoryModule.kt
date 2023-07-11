package com.vmlt.cinema.presentation.di

import com.vmlt.cinema.data.db.MovieDao
import com.vmlt.cinema.data.db.SessionDao
import com.vmlt.cinema.data.repository.MovieCacheImpl
import com.vmlt.cinema.data.repository.MovieRepositoryImpl
import com.vmlt.cinema.domain.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieRepositoryModule {

    @Provides
    fun provideMovieCache(movieDao: MovieDao, sessionDao: SessionDao) : MovieCacheImpl =
        MovieCacheImpl(movieDao, sessionDao)

    @Provides
    @Singleton
    fun provideMovieRepository(movieCache: MovieCacheImpl) : MovieRepository =
        MovieRepositoryImpl(movieCache)
}
