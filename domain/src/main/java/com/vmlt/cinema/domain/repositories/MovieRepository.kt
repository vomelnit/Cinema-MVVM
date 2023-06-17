package com.vmlt.cinema.domain.repositories

import com.vmlt.cinema.domain.entities.Movie
import com.vmlt.cinema.domain.entities.MovieBasicInfo
import com.vmlt.cinema.domain.entities.TicketsInfo

interface MovieRepository {
    fun getEntireBasicInfoMovieList(): List<MovieBasicInfo>
    fun getMovieDetailsById(movieId: Int): Movie?
    fun getAvailableTicketAmountByMovieId(movieId: Int): TicketsInfo?
    fun getMovieNameById(movieId: Int): String?
    fun updateAvailableTicketAmountByMovieId(movieId: Int, availableTicketsAmount: Int)
}