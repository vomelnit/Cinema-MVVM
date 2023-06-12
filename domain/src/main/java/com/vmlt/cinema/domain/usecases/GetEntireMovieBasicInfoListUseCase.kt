package com.vmlt.cinema.domain.usecases

import com.vmlt.cinema.domain.entities.MovieBasicInfo
import com.vmlt.cinema.domain.repositories.MovieRepository

class GetEntireMovieBasicInfoListUseCase(private val movieRepository: MovieRepository) {

    fun execute(): List<MovieBasicInfo> {
        return movieRepository.getEntireBasicInfoMovieList()
    }
}