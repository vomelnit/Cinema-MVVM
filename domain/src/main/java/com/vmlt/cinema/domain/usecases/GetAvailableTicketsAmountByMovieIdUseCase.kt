package com.vmlt.cinema.domain.usecases

import com.vmlt.cinema.domain.entities.TicketsInfo
import com.vmlt.cinema.domain.repositories.MovieRepository
import javax.inject.Inject

class GetAvailableTicketsAmountByMovieIdUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun execute(movieId: Int): TicketsInfo? {
        return movieRepository.getAvailableTicketAmountByMovieId(movieId)
    }
}