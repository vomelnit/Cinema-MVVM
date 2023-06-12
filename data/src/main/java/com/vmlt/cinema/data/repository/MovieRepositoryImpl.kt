package com.vmlt.cinema.data.repository

import com.vmlt.cinema.domain.entities.Movie
import com.vmlt.cinema.domain.entities.MovieBasicInfo
import com.vmlt.cinema.domain.entities.TicketsInfo
import com.vmlt.cinema.domain.repositories.MovieRepository

class MovieRepositoryImpl(private val movieCache: MovieCacheImpl) : MovieRepository {
    override fun getEntireBasicInfoMovieList(): List<MovieBasicInfo> {
        return movieCache.getAllMovies()
    }

    override fun getMovieDetailsById(movieId: Int): Movie? {
        return movieCache.getMovieDetailsById(movieId)
    }

    override fun getAvailableTicketAmountByMovieId(movieId: Int): TicketsInfo {
        return movieCache.getTicketsInfoById(movieId)
    }

    override fun buyTicketByMovieId(movieId: Int): Unit {
        movieCache.buyTicketById(movieId)
    }

    override fun returnTicketByMovieId(movieId: Int): Unit {
        movieCache.returnTicketById(movieId)
    }

    override fun getMovieNameById(movieId: Int): String? {
        return movieCache.getMovieNameById(movieId)
    }
}