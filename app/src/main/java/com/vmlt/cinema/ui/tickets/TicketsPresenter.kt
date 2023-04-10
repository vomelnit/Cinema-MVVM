package com.vmlt.cinema.ui.tickets

import com.vmlt.cinema.utils.MovieUtils
import com.vmlt.cinema.utils.TicketsUtils
import java.lang.ref.WeakReference

class TicketsPresenter(
    private val ticketsView: TicketsContract.TicketsView,
    private val movieModel: MovieUtils,
    private val ticketsModel: TicketsUtils
) :
    TicketsContract.TicketsPresenter {

    private val ticketsViewReference: WeakReference<TicketsContract.TicketsView> =
        WeakReference(ticketsView)

    override fun getMovieName(movieId: Int): String? {
        return movieModel.getMovieNameById(movieId)
    }

    override fun getMovieTickets(movieId: Int): Int? {
        return ticketsModel.getTicketsQuantityForMovie(movieId)
    }

    override fun buyTicketFromCinema(movieId: Int) {
        ticketsModel.removeTicketFromMovie(movieId)
        val ticketsView = ticketsViewReference.get()
        if (ticketsView != null) {
            ticketsModel.getTicketsQuantityForMovie(movieId)
                ?.let { ticketsAmount -> ticketsView.updateAvailableTicketsAmount(ticketsAmount) }
        }
    }

    override fun returnTicketToCinema(movieId: Int) {
        ticketsModel.addTicketFromMovie(movieId)
        val ticketsView = ticketsViewReference.get()
        if (ticketsView != null) {
            ticketsModel.getTicketsQuantityForMovie(movieId)
                ?.let { ticketsAmount -> ticketsView.updateAvailableTicketsAmount(ticketsAmount) }
        }
    }

}