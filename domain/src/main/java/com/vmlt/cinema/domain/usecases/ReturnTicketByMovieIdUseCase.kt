package com.vmlt.cinema.domain.usecases

import com.vmlt.cinema.domain.repositories.MovieRepository

class ReturnTicketByMovieIdUseCase(private val movieRepository: MovieRepository) {

    fun execute(movieId: Int) {
        movieRepository.returnTicketByMovieId(movieId)
    }
}