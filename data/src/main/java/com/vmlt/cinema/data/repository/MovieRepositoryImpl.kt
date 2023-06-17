package com.vmlt.cinema.data.repository

import com.vmlt.cinema.data.entities.MovieEntity
import com.vmlt.cinema.domain.entities.Movie
import com.vmlt.cinema.domain.entities.MovieBasicInfo
import com.vmlt.cinema.domain.entities.TicketsInfo
import com.vmlt.cinema.domain.repositories.MovieRepository
import java.util.Collections

class MovieRepositoryImpl(private val movieCache: MovieCacheImpl) : MovieRepository {
    override fun getEntireBasicInfoMovieList(): List<MovieBasicInfo> {
        val movieBasicInfoList = mutableListOf<MovieBasicInfo>()
        for (movie in movieCache.getAllMovies()) {
            movieBasicInfoList.add(MovieBasicInfo(movie.id, movie.name, movie.iconId))
        }
        return Collections.unmodifiableList(movieBasicInfoList)
    }

    override fun getMovieDetailsById(movieId: Int): Movie? {
        val movieEntity: MovieEntity? = movieCache.getMovieDetailsById(movieId)
        movieEntity?.let {
            return Movie(
                movieEntity.id,
                movieEntity.name,
                movieEntity.rating,
                movieEntity.iconId,
                movieEntity.year,
                movieEntity.genre
            )
        }
        return null
    }

    override fun getAvailableTicketAmountByMovieId(movieId: Int): TicketsInfo? {
        return movieCache.getTicketsInfoById(movieId)
    }

    override fun updateAvailableTicketAmountByMovieId(movieId: Int, availableTicketsAmount: Int) {
        return movieCache.updateAvailableTicketAmountByMovieId(movieId, availableTicketsAmount)
    }

    override fun getMovieNameById(movieId: Int): String? {
        return movieCache.getMovieNameById(movieId)
    }
}