package com.vmlt.cinema.utils

object TicketsUtils {
    private val defaultTicketsMap: Map<Int, Int> = generateTicketsList()
    private val ticketsMap: MutableMap<Int, Int> = defaultTicketsMap.toMutableMap()

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