package com.vmlt.cinema.domain.usecases

import com.vmlt.cinema.domain.repositories.MovieRepository

class BuyTicketByMovieIdUseCase(private val movieRepository: MovieRepository) {

    fun execute(movieId: Int) {
        movieRepository.buyTicketByMovieId(movieId)
    }
}