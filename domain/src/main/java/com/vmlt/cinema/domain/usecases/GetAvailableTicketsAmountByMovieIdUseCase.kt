package com.vmlt.cinema.domain.usecases

import com.vmlt.cinema.domain.entities.TicketsInfo
import com.vmlt.cinema.domain.repositories.MovieRepository

class GetAvailableTicketsAmountByMovieIdUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(movieId: Int): TicketsInfo? {
        return movieRepository.getAvailableTicketAmountByMovieId(movieId)
    }
}