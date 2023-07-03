package com.vmlt.cinema.domain.usecases

import com.vmlt.cinema.domain.entities.Movie
import com.vmlt.cinema.domain.repositories.MovieRepository
import javax.inject.Inject

class GetMovieDetailsByIdUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun execute(movieId: Int): Movie? {
        return movieRepository.getMovieDetailsById(movieId)
    }

}