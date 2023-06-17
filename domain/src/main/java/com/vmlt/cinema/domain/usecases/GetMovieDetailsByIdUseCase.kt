package com.vmlt.cinema.domain.usecases

import com.vmlt.cinema.domain.entities.Movie
import com.vmlt.cinema.domain.repositories.MovieRepository

class GetMovieDetailsByIdUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(movieId: Int): Movie? {
        return movieRepository.getMovieDetailsById(movieId)
    }

}