package com.vmlt.cinema.domain.usecases

import com.vmlt.cinema.domain.repositories.MovieRepository

class BuyTicketByMovieIdUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(movieId: Int) {
        val currentTicketsAmount: Int? =
            movieRepository.getAvailableTicketAmountByMovieId(movieId)?.ticketsAmount

        currentTicketsAmount?.let { tickets ->
            if (tickets >= 1) {
                movieRepository.updateAvailableTicketAmountByMovieId(movieId, tickets - 1)
            }
        }
    }
}