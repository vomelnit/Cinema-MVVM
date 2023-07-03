package com.vmlt.cinema.domain.usecases

import com.vmlt.cinema.domain.entities.MovieBasicInfo
import com.vmlt.cinema.domain.repositories.MovieRepository
import javax.inject.Inject

class GetEntireMovieBasicInfoListUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun execute(): List<MovieBasicInfo> {
        return movieRepository.getEntireBasicInfoMovieList()
    }
}