package com.vmlt.cinema.data.repository

import com.vmlt.cinema.data.R
import com.vmlt.cinema.data.db.CinemaDatabase
import com.vmlt.cinema.data.db.MockDatabase
import com.vmlt.cinema.data.db.MovieDao
import com.vmlt.cinema.data.db.SessionDao
import com.vmlt.cinema.data.entities.MovieEntity
import com.vmlt.cinema.data.entities.SessionEntity
import com.vmlt.cinema.domain.entities.Movie
import com.vmlt.cinema.domain.entities.MovieBasicInfo
import com.vmlt.cinema.domain.entities.TicketsInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import kotlin.coroutines.CoroutineContext

class MovieCacheImpl(val cinemaDatabase: CinemaDatabase) {

    private val movieDao: MovieDao = cinemaDatabase.getMovieDao()
    private val sessionDao: SessionDao = cinemaDatabase.getSessionDao()

    init {
        val context: CoroutineContext = Dispatchers.IO
        val scope = CoroutineScope(context + Job())
        scope.launch {
            movieDao.insert(
                MovieEntity(
                    1,
                    "The Strays",
                    6.5f,
                    R.drawable.the_strays_poster,
                    2023,
                    "Thriller, Drama"
                )
            )
            movieDao.insert(
                MovieEntity(
                    2,
                    "The Whale",
                    7.7f,
                    R.drawable.the_whale_poster,
                    2022,
                    "Drama"
                )
            )
            movieDao.insert(
                MovieEntity(
                    3,
                    "All that breathes",
                    6.5f,
                    R.drawable.all_that_breathes_poster,
                    2010,
                    "Horror"
                )
            )
            movieDao.insert(
                MovieEntity(
                    4,
                    "We have a ghost",
                    3.3f,
                    R.drawable.we_have_a_ghost_poster,
                    2018,
                    "Comedy"
                )
            )

            sessionDao.insert(SessionEntity(1, 1, 33, 44,12.50, 1))
            sessionDao.insert(SessionEntity(2, 2, 24, 47,13.99, 2))
            sessionDao.insert(SessionEntity(3, 3, 50, 100,9.99, 3))
            sessionDao.insert(SessionEntity(4, 4, 4, 20,12.09, 2))
        }
    }

    //    private val mockDatabase: MockDatabase = MockDatabase()
    fun  getAllMovies(): List<MovieEntity> {
        return movieDao.getAllMovies()
    }

    fun getMovieDetailsById(movieId: Int): MovieEntity? {
        return movieDao.getMovieById(movieId)
    }

    fun getMovieNameById(movieId: Int): String? {
        return movieDao.getMovieNameById(movieId)
    }

    fun getTicketsInfoById(movieId: Int): TicketsInfo? {

        return TicketsInfo(
            movieId,
            movieDao.getMovieNameById(movieId),
            sessionDao.getTicketAmountByMovieId(movieId),
            sessionDao.getMaxTicketAmountByMovieId(movieId)
        )
    }

    fun updateAvailableTicketAmountByMovieId(movieId: Int, availableTicketsAmount: Int) {
        sessionDao.updateAvailableTicketAmountByMovieId(movieId, availableTicketsAmount)
    }

}