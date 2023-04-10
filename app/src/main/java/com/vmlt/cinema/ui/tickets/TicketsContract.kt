package com.vmlt.cinema.ui.tickets

interface TicketsContract {
    interface TicketsView {
        fun updateAvailableTicketsAmount(ticketsAmount: Int)
    }

    interface TicketsPresenter {
        fun getMovieName(movieId: Int): String?
        fun getMovieTickets(movieId: Int): Int?
        fun buyTicketFromCinema(movieId: Int)
        fun returnTicketToCinema(movieId: Int)
    }
}