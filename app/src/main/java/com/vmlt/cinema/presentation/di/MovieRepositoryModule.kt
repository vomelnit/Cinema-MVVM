package com.vmlt.cinema.presentation.di

import com.vmlt.cinema.data.repository.MovieRepositoryImpl
import com.vmlt.cinema.domain.repositories.MovieRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
abstract class MovieRepositoryModule() {

    @Singleton
    @Binds
    abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

}
