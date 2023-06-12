package com.vmlt.cinema.data.db

import com.vmlt.cinema.data.R
import com.vmlt.cinema.domain.entities.Movie
import com.vmlt.cinema.domain.entities.MovieBasicInfo

class MockDatabase {
    private val movieList: List<Movie> = generateMoviesList()
    private val defaultTicketsMap: Map<Int, Int> = generateTicketsList()
    private val ticketsMap: MutableMap<Int, Int> = defaultTicketsMap.toMutableMap()

    fun getAllAvailableMoviesBasicInfo(): List<MovieBasicInfo> {
        val movieBasicInfoList: MutableList<MovieBasicInfo> = mutableListOf()

        movieList.forEach { movie ->
            movieBasicInfoList.add(
                MovieBasicInfo(
                    movie.id,
                    movie.name,
                    movie.iconId
                )
            )
        }

        return movieBasicInfoList
    }

    fun getMovieInfoById(movieId: Int): Movie? {
        return movieList.firstOrNull { it.id == movieId }
    }

    fun getMovieNameById(movieId: Int): String? {
        return movieList.firstOrNull { it.id == movieId }?.name
    }

    /* Mock function */
    private fun generateMoviesList(): List<Movie> {
        return listOf(
            Movie(1, "The Strays", 6.5f, R.drawable.the_strays_poster, 2023, "Thriller, Drama"),
            Movie(2, "The Whale", 7.7f, R.drawable.the_whale_poster, 2022, "Drama"),
            Movie(
                3,
                "All that breathes",
                6.5f,
                R.drawable.all_that_breathes_poster,
                2010,
                "Horror"
            ),
            Movie(4, "We have a ghost", 3.3f, R.drawable.we_have_a_ghost_poster, 2018, "Comedy"),
        )
    }

    /* Mock function */
    private fun generateTicketsList(): MutableMap<Int, Int> {
        return mutableMapOf(
            1 to 12,
            2 to 33,
            3 to 49,
            4 to 8
        )
    }

    fun getTicketsQuantityForMovie(movieId: Int): Int? {
        return ticketsMap[movieId]
    }

    fun removeTicketFromMovie(movieId: Int) {
        ticketsMap[movieId]?.let { availableTickets ->
            if (availableTickets > 0) {
                ticketsMap[movieId] = availableTickets - 1
            }
        }
    }

    fun addTicketFromMovie(movieId: Int) {
        val defaultAmount = defaultTicketsMap[movieId] ?: return

        ticketsMap[movieId]?.let { availableTickets ->
            if (availableTickets < defaultAmount) {
                ticketsMap[movieId] = availableTickets + 1
            }
        }
    }

}