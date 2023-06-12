package com.vmlt.cinema.data.repository

import com.vmlt.cinema.data.db.MockDatabase
import com.vmlt.cinema.domain.entities.Movie
import com.vmlt.cinema.domain.entities.MovieBasicInfo
import com.vmlt.cinema.domain.entities.TicketsInfo

class MovieCacheImpl {
    private val mockDatabase: MockDatabase = MockDatabase()
    fun getAllMovies(): List<MovieBasicInfo> {
        return mockDatabase.getAllAvailableMoviesBasicInfo()
    }

    fun getMovieDetailsById(movieId: Int): Movie? {
        return mockDatabase.getMovieInfoById(movieId)
    }

    fun getMovieNameById(movieId: Int): String? {
        return mockDatabase.getMovieNameById(movieId)
    }

    fun getTicketsInfoById(movieId: Int): TicketsInfo {
        return TicketsInfo(
            movieId,
            mockDatabase.getMovieNameById(movieId),
            mockDatabase.getTicketsQuantityForMovie(movieId)
        )
    }

    fun buyTicketById(movieId: Int) {
        mockDatabase.removeTicketFromMovie(movieId)
    }

    fun returnTicketById(movieId: Int) {
        mockDatabase.addTicketFromMovie(movieId)
    }
}