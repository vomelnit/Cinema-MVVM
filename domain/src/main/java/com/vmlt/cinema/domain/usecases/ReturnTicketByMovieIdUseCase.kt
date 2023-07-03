package com.vmlt.cinema.domain.usecases

import com.vmlt.cinema.domain.entities.TicketsInfo
import com.vmlt.cinema.domain.repositories.MovieRepository
import javax.inject.Inject

class ReturnTicketByMovieIdUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun execute(movieId: Int) {
        val ticketInfo: TicketsInfo = movieRepository.getAvailableTicketAmountByMovieId(movieId)
            ?: return

        if ((ticketInfo.ticketsAmount != null) && (ticketInfo.maxTicketsAmount != null)) {
            if (ticketInfo.maxTicketsAmount > ticketInfo.ticketsAmount) {
                movieRepository.updateAvailableTicketAmountByMovieId(
                    movieId,
                    ticketInfo.ticketsAmount + 1
                )
            }
        }
    }

}