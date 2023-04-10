package com.vmlt.cinema.utils

object TicketsUtils {
    private val ticketsMap: MutableMap<Int, Int> = generateTicketsList()

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
        ticketsMap[movieId]?.let {
            if (it > 0) {
                ticketsMap[movieId] = it - 1
            }
        }
    }

    fun addTicketFromMovie(movieId: Int) {
        ticketsMap[movieId]?.let {
            ticketsMap[movieId] = it + 1
        }
    }
}